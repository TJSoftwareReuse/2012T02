<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>同济大学电子邮件系统</title>
<link href="/coremail/common/index_cm20/login/login.css" rel="stylesheet" type="text/css" charset="iso-8859-1">
<script type="text/javascript"><!--


function getCookie(name) {
    var reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)", "gi");
    var tmp = reg.exec(document.cookie);
    return unescape((tmp || [])[2] || "");
}
window.onload=function(){
   var cookie=getCookie("uid");
   if(cookie!=null&&cookie!=""){
       document.getElementsByName("password")[0].focus();
   }else{
       document.getElementsByName("uid")[0].focus();
   }
   //假如是firefox则隐藏隐藏设置主页
   if(!document.all) {
       var homepage = document.getElementById("homepage");
       if(homepage){
           homepage.style.display = "none";
       }
   }
}
function setCookie(name, value) {
    document.cookie = name + '=' + escape(value)
            + ";expires=" + (new Date(2099, 12, 31)).toGMTString();
}

function restoreCookies() {
    document.getElementsByName("uid")[0].value = getCookie("uid");
}

function loginSubmit(form, event) {
    if (document.getElementById("saveUsername").checked) {
        setCookie("uid", document.getElementsByName("uid")[0].value);
    }

    if ((document.getElementById("face_classic_cgi") || {}).selected) {
        if (document.all) { // is IE
            event.returnValue = false;
        }
        document.getElementById("classic_cgi_form").elements["user"].value = form.elements["uid"].value;
        document.getElementById("classic_cgi_form").elements["pass"].value = form.elements["password"].value;
        document.getElementById("classic_cgi_form").submit();
        return false;
    }
    changeAction();
    return true;
}
function bookmarkMe() {
    try {
        window.external.AddFavorite(location.href, document.title);
    } catch(e) {
        alert("请按 Ctrl-D 收藏本页")
    }
}

function recoverPwd(anchorElement) {
    anchorElement.href += '?uid='+ document.getElementById("uid").value;
}

var httpsAction = "https://" + location.hostname + ":443" + "/coremail" + "/index.jsp?sid=";
var loginFromAction = "http://" + location.hostname + ":80" + "/coremail" + "/index.jsp?sid=";

function changeAction() {
    var form = document.getElementById("loginForm");
    var checkbox = document.getElementById("usessl");
    if(checkbox.checked) {
       form.action = httpsAction;
    } else {
       form.action = loginFromAction;
    }
}

//--></script>
</head>

<body>
  <div class="Head"></div>
  
  <div class="Main">
    <div class="MainTop">
      <div class="MainTopLeft">
        <div class="logo">
        <img src="/coremail/common/index_cm20/login/logo.gif" alt="Coremail 电子邮件系统">
        </div>
      </div>
      <div class="MainTopRight">
        <div class="MainTopLinks">
          
          <span id="homepage"><a  class="MTLinks" href="javascript:;" onClick="this.style.behavior='url(#default#homepage)';this.setHomePage(document.location);">
            设为首页</a>
            |
          </span>    
          
          <a class="MTLinks" href="javascript:bookmarkMe()">
            收藏本页</a>
            |
          
          
          <a target="_blank" href="/coremail/help/index_zh_CN.jsp">
            帮助</a>
        </div>
      </div>
    </div>
    <div class="MainM">
        <div class="MainLeft">
          
          
            <div class="slogan1">
              
                  <div><BR>邮件系统全面支持SSL及移动客户端(iphone/ipad/android)<BR>请点击右上角帮助查看如何进行设置</div>
                
            </div>
          
            <div class="slogan2">
              
                  <div>POP3服务器：   pop.tongji.edu.cn<BR>SMTP服务器： smtp.tongji.edu.cn<BR>IMAP服务器： imap.tongji.edu.cn</div>
                
            </div>
          
            <div class="slogan3">
              
                  <div>联系邮件：helpdesk[AT]tongji.edu.cn<BR>联系电话：021-65989006</div>
                
            </div>
          
        </div>

        <div class="MainRight">
          
            
            
              
            
          
          <form id="loginForm" method="post" action="/coremail/index.jsp"
                onsubmit="return loginSubmit(this, event);">
            
            <table class="loginTable">
              <caption class="title">
                电子邮箱登录
                <span class="titleNoteError"></span>
              </caption>
              <tfoot>
                <tr>
                  <td></td>
                  <td colspan="2">
                    <button name="action:login" type="submit" class="Button">
                      登　录
                    </button>
                    <button name="register" type="button"  class="Button" onclick="window.location.href='http://mail.tongji.edu.cn:9900/activate/register.jsp'">
                  注&nbsp;&nbsp;册
                    </button><br><br>
                    

                    
                    
 
                    
                        
                        <a href="/coremail/forgetpwd.jsp" onclick="recoverPwd(this);">
                          忘记密码？
                        </a>
                    
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a  target="_blank" href="/coremail/common/index_cm20/help/register.doc">注册帮助</a>
                  </td>
                </tr>
              </tfoot>
              <tr>
                <th>用户名:</th>
                <td><input type="text" id="uid" name="uid" class="input"
                           value=""></td>
                <td class="error"></td>
              </tr>
              <tr>
                <th>密　码:</th>
                <td><input type="password" name="password" class="input" autocomplete="off"
                           value=""></td>
                <td class="error"></td>
              </tr>
              

         <tr>
                  




    
        
               <th>
                      <label>语　言
               </th>
                   <td>   




<select name="locale" id="locale" class="inputWidth">
    
        <option value="zh_CN" selected>简体中文</option>
    
        <option value="zh_TW" >繁體中文</option>
    
        <option value="en_US" >English</option>
    
</select>

 </td>
                  </div>
                  
    
    



         </tr>
         <tr>
       <th>
        <input type="checkbox" id="usessl" checked value="true" onclick="changeAction();">
        </th>
        <td>
          <label for="usessl"> SSL安全登录
                  </label>
        </td>
         </tr>
         <tr>
        <th>
                  <input type="checkbox" id="saveUsername">
                </th>
                <td colspan="2">
                  <label for="saveUsername">
                    在此电脑上记住用户名
                  </label>
                </td>
              </tr>
            </table>
          </form>
        </div>
    </div>
    <div class="copyright">
        <a href="http://www.coremail.cn" target="_blank">同济大学 &#169; Copyright 2008 - 2012</a>    
    </div>
  </div>

  <script type="text/javascript"><!--
  restoreCookies();
  //--></script>

  
  <form action="/coremail/fcg/login" id="classic_cgi_form" method="post">
    <input type="hidden" name="user" />
    <input type="hidden" name="pass" />
  </form>

</body>
</html>
