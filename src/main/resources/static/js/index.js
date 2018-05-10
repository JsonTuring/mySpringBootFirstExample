
$('#btn').on('click', function () {
    alert('hello world');
    $.ajax({
        url: '/getInterfaceData',
        type: 'get',
        dataType: 'json',
        data: {
            jgmc:"虹口区凉城新村街道社区卫生服务中心",
            // hisypbm:"092"
            hisypbm:"092"
        },
        success: function (data) {
            alert(JSON.stringify(data));
        }
    })
});