/**
 * layer中提示框图标描述
 * 1.√
 * 2.×
 * 3.?
 * 4.锁
 * 5.哭脸
 * 6.笑脸
 * 7以上.感叹号
 * @type {string}
 */


var WASAPI = "http://10.45.0.54:8765";//远程接口调用地址
var URL = "http://localhost:8081/";
//var URL = "http://47.98.60.240:8081/";
var USER =  getCookie("USER")==""?null:JSON.parse( getCookie("USER"));
var MENU = getCookie("MENU")==""?null:JSON.parse( getCookie("MENU"));

//设置cookie
function setCookie(c_name, value, expiredays) {
    var exdate = new Date();
    if (expiredays != -1) {
        exdate.setTime(Number(exdate) + expiredays);
    } else {
        exdate.setTime(expiredays);
    }
    document.cookie = c_name + "=" + escape(value) + ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString());
}
//获取cookie
function getCookie(c_name) {
    if(document.cookie.length > 0) {
        c_start = document.cookie.indexOf(c_name + "=");//获取字符串的起点
        if(c_start != -1) {
            c_start = c_start + c_name.length + 1;//获取值的起点
            c_end = document.cookie.indexOf(";", c_start);//获取结尾处
            if(c_end == -1) c_end = document.cookie.length;//如果是最后一个，结尾就是cookie字符串的结尾
            return  unescape(document.cookie.substring(c_start, c_end));//截取字符串返回
        }
    }
    return "";
}
//清除cookie
function clearCookie(name) {
    setCookie(name, "", -1);
}

/**
 * 展开左边栏
 */
function showMenu() {
    if($('.left-nav').css('left')=='0px'){
        $('.left-nav').animate({left: '-221px'}, 100);
        $('.page-content').animate({left: '0px'}, 100);
        $('.page-content-bg').hide();
    }else{
        $('.left-nav').animate({left: '0px'}, 100);
        $('.page-content').animate({left: '221px'}, 100);
        if($(window).width()<768){
            $('.page-content-bg').show();
        }
    }
}
/**
 * 获取地址栏参数
 * @returns {Object}
 */
function getRequest() {
    var url = window.location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for(var i = 0; i < strs.length; i ++) {

            theRequest[strs[i].split("=")[0]]=decodeURI(strs[i].split("=")[1]);

        }
    }
    return theRequest;
}