package com.imooc.service;

import com.imooc.action.UserController;
import com.imooc.dao.UserDao;
import com.imooc.domain.InterfaceResult;
import com.imooc.enums.InterfaceResultEnum;
import com.imooc.frame.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author zhuguoxiang
 * @date 2018/04/27
 */
@Service
public class UserServiceImpl extends BaseService implements UserService {
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    UserDao userDao;
    @Resource
    private JavaMailSender mailSender;
    @Value("${forgotpassword.url}")
    private String forgotpasswordUrl;
    @Value("${mail.content.forgotpassword}")
    private String mailContent;
    @Value("${spring.mail.username}")
    private String mailFrom;
    @Value("${mail.subject.forgotpassword}")
    private String mailSubject;

    @Override
    public Map login(Map map) {
        String pwd = getEncryptedPwd(map.get("password").toString());
        Map resultMap = MapUtil.keysLowerCase(
                userDao.getUserInfoByUsernameAndPwd(
                        map.get("username").toString(), pwd));
        return resultMap;
    }



    @Override
    public InterfaceResult register(Map map) {
        try {
            //email不许为空
            if(StringUtil.isEmpty(map.get("email"))){
                return InterfaceResultUtil.error(InterfaceResultEnum.EMAIL_NOT_NULL);
            }
            //用户名不许为空
            if(StringUtil.isEmpty(map.get("username"))){
                return InterfaceResultUtil.error(InterfaceResultEnum.USER_NOT_NULL);
            }
            //密码不许为空
            if(StringUtil.isEmpty(map.get("password"))){
                return InterfaceResultUtil.error(InterfaceResultEnum.PASSWORD_NOT_NULL);
            }

            //判断邮箱是否已被注册
            Map userMap = userDao.findByEmail(map.get("email").toString());
            if (null != userMap) {
                return InterfaceResultUtil.error(InterfaceResultEnum.EMAIL_USED);
            }
            //判断用户名是否已被注册
            Map currUserMap = userDao.findByUserName(map.get("username").toString());
            if (null != currUserMap) {
                return InterfaceResultUtil.error(InterfaceResultEnum.USERNAME_USED);
            }
            //保存用户注册信息
            Date currDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            map.put("createtime",sdf.format(currDate));
            map.put("id", UUID.randomUUID().toString());
            map.put("password",getEncryptedPwd(map.get("password").toString()));
            userDao.saveRegister(map);
            //注册成功，返回成功信息及登录页面路径
            return InterfaceResultUtil.success(InterfaceResultEnum.SUCCESS,"/login");
        } catch (Exception e){
            logger.error("用户注册错误："+e.toString());
            return InterfaceResultUtil.error(InterfaceResultEnum.UNKONW_ERROR);
        }
    }

    @Override
    public InterfaceResult sendForgotPasswordEmail(Map map) {
        try{
            String email = "";
            //email不许为空
            if(StringUtil.isEmpty(map.get("email"))){
                return InterfaceResultUtil.error(InterfaceResultEnum.EMAIL_NOT_NULL);
            }else {
                email = map.get("email").toString();
            }
            //判断邮箱是否未注册
            Map userMap = userDao.findByEmail(map.get("email").toString());
            if (null == userMap) {
                return InterfaceResultUtil.error(InterfaceResultEnum.EMAIL_NOT_REGISTER);
            }
            //密钥
            String secretKey = UUID.randomUUID().toString();
            //30分钟过期
            Timestamp outDate = new Timestamp(System.currentTimeMillis() + 30 * 60 * 1000);
            userDao.setOutDateAndValidataCode(outDate+"", secretKey, email);
            long date = outDate.getTime() / 1000 * 1000;
            String key = email + "$" + date + "$" + secretKey;
            //数字签名
            String digitalSignature = MD5Util.encrypt(key);
            String resetPassHref = forgotpasswordUrl + "?sid="
                    + digitalSignature +"&email="+email;
            String emailContent = MessageUtil.getMessage(mailContent, resetPassHref, forgotpasswordUrl);
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(mailFrom);
            helper.setTo(email);
            helper.setSubject(mailSubject);
            helper.setText(emailContent, true);
            mailSender.send(mimeMessage);
            return InterfaceResultUtil.success(InterfaceResultEnum.SUCCESS);
        }catch (Exception e){
            logger.error("用户发送忘记密码邮件错误："+e.toString());
            return InterfaceResultUtil.error(InterfaceResultEnum.UNKONW_ERROR);
        }
    }

    @Override
    public InterfaceResult setNewPassword(Map map) {
        try{
            //密码不许为空
            if(StringUtil.isEmpty(map.get("newpassword"))){
                return InterfaceResultUtil.error(InterfaceResultEnum.PASSWORD_NOT_NULL);
            }
            Map userMap = userDao.findByEmail(map.get("email").toString());
            Timestamp outDate = Timestamp.valueOf(userMap.get("outdate").toString());
            //表示已经过期
            if(outDate.getTime() <= System.currentTimeMillis()){
                return InterfaceResultUtil.error(InterfaceResultEnum.LINK_OUTDATED);
            }
            //数字签名
            String key = userMap.get("email").toString()+"$"+outDate.getTime()/1000*1000+"$"+userMap.get("validatecode").toString();
            String digitalSignature = MD5Util.encrypt(key);
            if(!digitalSignature.equals(map.get("sid").toString())) {
                return InterfaceResultUtil.error(InterfaceResultEnum.LINK_OUTDATED);
            }
            userDao.setNewPassword(getEncryptedPwd(map.get("newpassword").toString()), userMap.get("email").toString());
            return InterfaceResultUtil.success(InterfaceResultEnum.SUCCESS);
        }catch (Exception e){
            logger.error("用户设置新密码错误："+e.toString());
            return InterfaceResultUtil.error(InterfaceResultEnum.UNKONW_ERROR);
        }

    }
}