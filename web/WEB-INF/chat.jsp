<%--
  Created by IntelliJ IDEA.
  User: hocgin
  Date: 16-8-9
  Time: 下午10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>聊天室 - ${room}</title>
    <jsp:include page="../lib/tpl/header-dev.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/app/app.css">
</head>
<body>
<div id="progress">
    <div class="progress progress-height">
        <div id="progress-load" class="progress-bar progress-bar-info progress-bar-striped progress-height" role="progressbar">
        </div>
    </div>
</div>
<header class="navbar navbar-static-top bs-docs-nav" id="top" role="banner">
    <div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target="#bs-navbar"
                    aria-controls="bs-navbar" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="http://hocg.in/" class="navbar-brand">hocgin-Chat</a>
        </div>
        <nav id="bs-navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active">
                    <a href="http://github.com/hocgin">GitHub</a>
                </li>
                <li class="active">
                    <a href="http://weibo.com/u/2006617153">WeiBo</a>
                </li>
                <li class="active">
                    <a href="https://www.500px.com/hocgin">500px</a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="http://hocg.in/" target="_blank">Hocgin (๑`灬´๑) 博客</a></li>
            </ul>
        </nav>
    </div>
</header>
<div class="bs-docs-header" id="content" tabindex="-1">
    <div class="container">
        <h1>聊天室(${room})</h1>
        <p> 让我们一起high起来.. ᕦ(ò_óˇ)ᕤ</p>
    </div>
</div>
<%--聊天 Area top --%>
<div>
    <div class="center-block">
        <div class="container highlight margin-bottom-0">
            <div id="chart-area" class="overflow-auto"
                 style="height: 300px;"
            >
            </div>
        </div>
    </div>
</div>
<%--聊天 Area bottom --%>
<%--文字输入框 Area top--%>
<div>
    <div class="container padding-0">
        <%--功能栏 Area top--%>
        <div class="nav btn-group">
            <button type="button" id="expressions" class="btn btn-default">
                <span class="glyphicon glyphicon-sunglasses"></span>
            </button>
            <label for="picture" class="btn btn-default">
                <span class="glyphicon glyphicon-picture"></span>
                <form id="picture-form" method="post" enctype="multipart/form-data">
                    <input type="file" class="display-none" id="picture" name="file">
                </form>
            </label>
            <label for="files" class="btn btn-default">
                <span class="glyphicon glyphicon-file"></span>
                <form id="files-form" method="post" enctype="multipart/form-data">
                <input type="file" class="display-none" id="files" name="file">
                </form>
            </label>
        </div>
        <%--功能栏 Area bottom--%>
        <div class="sender-box">
            <div id="sender-textarea" class="overflow-auto"
                 placeholder="让我们High起来.. ᕦ(ò_óˇ)ᕤ"
                 contenteditable="true"
            ></div>
            <div id="faces-area" class="overflow-auto"></div>
        </div>
        <div class="send-btn pull-right">
            <button id="clean-btn" class="btn btn-warning" type="submit"
                    data-toggle="tooltip" data-placement="bottom" title="Ctrl + Del">
                <span class="glyphicon glyphicon-trash"></span> 清空
            </button>
            <button id="send-btn" class="btn btn-primary" type="submit"
                    data-toggle="tooltip" data-placement="bottom" title="Ctrl + Enter">
                <span class="glyphicon glyphicon-send"></span> 发送
            </button>
        </div>
    </div>
</div>
<%--文字输入框 Area bottom--%>
<script>
    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
        var textareaHandler = {
            facePath: [
                {faceName: "微笑", facePath: "0_微笑.gif"},
                {faceName: "撇嘴", facePath: "1_撇嘴.gif"},
                {faceName: "色", facePath: "2_色.gif"},
                {faceName: "发呆", facePath: "3_发呆.gif"},
                {faceName: "得意", facePath: "4_得意.gif"},
                {faceName: "流泪", facePath: "5_流泪.gif"},
                {faceName: "害羞", facePath: "6_害羞.gif"},
                {faceName: "闭嘴", facePath: "7_闭嘴.gif"},
                {faceName: "大哭", facePath: "9_大哭.gif"},
                {faceName: "尴尬", facePath: "10_尴尬.gif"},
                {faceName: "发怒", facePath: "11_发怒.gif"},
                {faceName: "调皮", facePath: "12_调皮.gif"},
                {faceName: "龇牙", facePath: "13_龇牙.gif"},
                {faceName: "惊讶", facePath: "14_惊讶.gif"},
                {faceName: "难过", facePath: "15_难过.gif"},
                {faceName: "酷", facePath: "16_酷.gif"},
                {faceName: "冷汗", facePath: "17_冷汗.gif"},
                {faceName: "抓狂", facePath: "18_抓狂.gif"},
                {faceName: "吐", facePath: "19_吐.gif"},
                {faceName: "偷笑", facePath: "20_偷笑.gif"},
                {faceName: "可爱", facePath: "21_可爱.gif"},
                {faceName: "白眼", facePath: "22_白眼.gif"},
                {faceName: "傲慢", facePath: "23_傲慢.gif"},
                {faceName: "饥饿", facePath: "24_饥饿.gif"},
                {faceName: "困", facePath: "25_困.gif"},
                {faceName: "惊恐", facePath: "26_惊恐.gif"},
                {faceName: "流汗", facePath: "27_流汗.gif"},
                {faceName: "憨笑", facePath: "28_憨笑.gif"},
                {faceName: "大兵", facePath: "29_大兵.gif"},
                {faceName: "奋斗", facePath: "30_奋斗.gif"},
                {faceName: "咒骂", facePath: "31_咒骂.gif"},
                {faceName: "疑问", facePath: "32_疑问.gif"},
                {faceName: "嘘", facePath: "33_嘘.gif"},
                {faceName: "晕", facePath: "34_晕.gif"},
                {faceName: "折磨", facePath: "35_折磨.gif"},
                {faceName: "衰", facePath: "36_衰.gif"},
                {faceName: "骷髅", facePath: "37_骷髅.gif"},
                {faceName: "敲打", facePath: "38_敲打.gif"},
                {faceName: "再见", facePath: "39_再见.gif"},
                {faceName: "擦汗", facePath: "40_擦汗.gif"},
                {faceName: "抠鼻", facePath: "41_抠鼻.gif"},
                {faceName: "鼓掌", facePath: "42_鼓掌.gif"},
                {faceName: "糗大了", facePath: "43_糗大了.gif"},
                {faceName: "坏笑", facePath: "44_坏笑.gif"},
                {faceName: "左哼哼", facePath: "45_左哼哼.gif"},
                {faceName: "右哼哼", facePath: "46_右哼哼.gif"},
                {faceName: "哈欠", facePath: "47_哈欠.gif"},
                {faceName: "鄙视", facePath: "48_鄙视.gif"},
                {faceName: "委屈", facePath: "49_委屈.gif"},
                {faceName: "快哭了", facePath: "50_快哭了.gif"},
                {faceName: "阴险", facePath: "51_阴险.gif"},
                {faceName: "亲亲", facePath: "52_亲亲.gif"},
                {faceName: "吓", facePath: "53_吓.gif"},
                {faceName: "可怜", facePath: "54_可怜.gif"},
                {faceName: "菜刀", facePath: "55_菜刀.gif"},
                {faceName: "西瓜", facePath: "56_西瓜.gif"},
                {faceName: "啤酒", facePath: "57_啤酒.gif"},
                {faceName: "篮球", facePath: "58_篮球.gif"},
                {faceName: "乒乓", facePath: "59_乒乓.gif"},
                {faceName: "拥抱", facePath: "78_拥抱.gif"},
                {faceName: "握手", facePath: "81_握手.gif"},
                {faceName: "得意地笑", facePath: "得意地笑.gif"},
                {faceName: "听音乐", facePath: "听音乐.gif"}
            ],
            basePath:"/lib/face/",
            init: function () {
                var that = this;
                var isShowImg = false;
                var $textarea = $("#sender-textarea");
                $textarea.focusout(function () {
                    $(this).parent().css("border-color", "#cccccc");
                    $(this).parent().css("box-shadow", "none");
                    $(this).parent().css("-moz-box-shadow", "none");
                    $(this).parent().css("-webkit-box-shadow", "none");
                });
                $textarea.focus(function () {
                    $(this).parent().css("border-color", "rgba(19,105,172,.75)");
                    $(this).parent().css("box-shadow", "0 0 3px rgba(19,105,192,.5)");
                    $(this).parent().css("-moz-box-shadow", "0 0 3px rgba(241,39,232,.5)");
                    $(this).parent().css("-webkit-box-shadow", "0 0 3px rgba(19,105,252,3)");
                });
                $("#expressions").click(function () {
                    var $facesArea = $("#faces-area");
                    if (isShowImg == false) {
                        isShowImg = true;
                        $facesArea.animate({marginTop: "-100px"}, 300);
                        if ($facesArea.children().length == 0) {
                            for (var i = 0; i < textareaHandler.facePath.length; i++) {
                                $("#faces-area").append("<img title=\"" + textareaHandler.facePath[i].faceName + "\" src=\""+that.basePath+"qq/" + textareaHandler.facePath[i].facePath + "\" >");
                            }
                            $facesArea.children("img").click(function () {
                                isShowImg = false;
                                $facesArea.animate({marginTop: "0px"}, 300);
                                textareaHandler.insertAtCursor($("#sender-textarea")[0], textareaHandler.translateForSelf("<;" + $(this).attr("title") + ">"));
//                                $textarea.data("message", textareaHandler.htmlToText($textarea.html()));
//                                console.log(textareaHandler.htmlToText($("#sender-textarea").html()))
                            });
                        }
                    } else {
                        isShowImg = false;
                        $facesArea.animate({marginTop: "0px"}, 300);
                    }
                });
            },
            /**
             *  文本框中插入img
             **/
            insertAtCursor: function (ele, tx) {
                ele.focus();
                $.tool.pasteHtmlAtCaret(tx, false)
            },
            /**
             *  翻译整个文本框的表情代码
             **/
            translate: function ($ele) {
                var text = $ele.html();
                var that = this;
                $.each(this.facePath, function (i, e) {
                    if (text.indexOf("&lt;;" + e.faceName + "&gt;") != -1) {
                        var imgTpl = "<img title=\"<;" + e.faceName + ">\" src=\""+that.basePath+"qq/" + e.facePath + "\" >";
                        text = text.replace(new RegExp("&lt;;" + e.faceName + "&gt;", "g"), imgTpl);
                        return true;
                    }
                });
                $ele.html(text);
            },
            /**
             * 把含表情文本转为img
             **/
            translateForSelf: function (key) {
                var text = "";
                var that = this;
                $.each(this.facePath, function (i, e) {
                    if (key.indexOf("<;" + e.faceName + ">") != -1) {
                        text = "<img title=\"<;" + e.faceName + ">\" src=\""+that.basePath+"qq/" + e.facePath + "\" >";
                        return false;
                    }
                });
                return text;
            },
            /**
             * 把含表情的html -》文本格式
             **/
            htmlToText: function (html) {
                var rts = html.match(new RegExp('<img title=\"<;' + ".*?" + '>\" src=\"\/lib\/face\/qq\/' + ".*?" + '\">', 'mg'));
                if (!!rts && rts.length > 0) {
                    for (var i = 0; i< rts.length; i ++) {
                        var rt = rts[i].match(new RegExp('<;' + ".*?" + '>', 'mg'));
                        if (!!rt) {
                            html = html.replace(rts[i], rt);
                        }
                    }
                }
                return html;
            }
        };
        textareaHandler.init();

        $("#clean-btn").click(function () { // 清空按钮
            var $textarea = $("#sender-textarea");
            if ($textarea.text().length > 0 && confirm("确认清空文本框内容?")) {
                $textarea.text("");
            }
        });

        $.messageHandler = {
            socket: null,
            name: "${user}",
            room: "${room}",
            init: function () {
               // 绑定发送按钮
                // 初始化socket
                var url = "ws://127.0.0.1:8080/ws/" + this.room +"/"+ this.name;
                this.initSocket(url);
                this.initSendBtn($('#send-btn'));
            },
            initSendBtn:function ($e) {
                var $textarea = $("#sender-textarea");
                var that = this;
                $e.on('click', function () {
                    var message = textareaHandler.htmlToText($textarea.html());
                    message = !!message ? message : "";
                    if (message.length > 0) {
                        // todo 发送动画
                        that.sendMessage("TEXT", message);
                    }
                });
            },
            initSocket: function (url) {
                var that = this;
                this.socket = new WebSocket(url);
                this.socket.onopen = function (e) { // 打开链接
                    console.log("正在连接中..")
//                    $('#chats').append("正在连接中.." + "<br/>");
                };
                this.socket.onmessage = function (msg) { // 服务端消息
//                    console.log((msg.data));
                    that.resolveReply(JSON.parse(msg.data));
                    $.tool.scrollEnd($('#chart-area'));
                };
                this.socket.onerror = function (e) { // 错误信息
                    console.log("error..." + e);
//                    $('#chats').append("error.." + e + "<br/>");
                };
                this.socket.onclose = function () { // 链接关闭
                    console.log("连接正在关闭 .. ");
                };
            },
            sendMessage: function(type, content){
                var receiver = this.room;
                var event = "";
                var receiverType = "Group";
                var sender = this.name;
                var senderType = "Personal";
                var tpl = '{' +
                            '"event":"' + event + '",' +
                            '"type":"' + type + '",' +
                            '"receiver":"' + receiver + '",' +
                            '"receiverType":"' + receiverType + '",' +
                            '"senderType":"' + senderType + '",' +
                            '"sender":"' + sender + '",' +
                            '"content":"' + content + '",' +
                            '"time":' + new Date().getTime() +
                        '}';
                if (!this.socket) {
                    alert("连接未建立");
                }else {
                    this.socket.send(tpl);
                }
            },
            /**
             * 解析响应文本
             **/
            resolveReply: function (json) {
                var name = json.receiver;
                var time = $.tool.format(new Date(json.time), "hh:mm:ss");
                var content = json.content;
                var header;
                if (this.name === name) { // 自己的消息
                     header = '<h4 class="myself-human"><mark>@'+name+'</mark>  <small>'+time+'</small></h4>';
                } else {
                     header = '<h4 class="other-human">'+name+'  <small>'+time+'</small></h4>';
                }
                var body;
                switch (json.type) {
                    case "IMAGE":
                        body = '<p><img src="' + content + '"/></p>';
                        break;
                    case "FILE":
                        body = '<a href="'+content+'">'+'<p><img class="upload-file" src="/i/icon-file.png"/></p>'+'</a>';
                        break;
                    case "TEXT":
                        $("#sender-textarea").html("");
                    default:
                        var $body = $('<p>'+ content +'</p>');
                        textareaHandler.translate($body);
                        body = $body.html();
                }
                var html = header + body;
                $('#chart-area').append(!!html? html:"");
            }
        };
        $.messageHandler.init();
        $.tool = {
            cursor: 0,
            moveEnd: function (ele) { // 光标移动到末尾
                ele.focus();
                var sel = window.getSelection();
                var range = document.createRange();
                range.selectNodeContents(ele);
                range.collapse(false);
                sel.removeAllRanges();
                sel.addRange(range);
            },
            scroll: function ($ele, pos, speed) {
                $ele.animate({scrollTop: pos}, !!speed? speed:1000);
            },
            scrollEnd:function ($ele) {
                $.tool.scroll($ele, $ele[0].scrollHeight + "px", $ele[0].scrollHeight);
            },
            /**
             * 在光标之前插入文本
             * @param html 要插入的文本
             * @param selectPastedContent 是否选中
             */
            pasteHtmlAtCaret:function(html, selectPastedContent) {
                var sel, range;
                if (window.getSelection) {
                    // IE9 and non-IE
                    sel = window.getSelection();
                    if (sel.getRangeAt && sel.rangeCount) {
                        range = sel.getRangeAt(0);
                        range.deleteContents();

                        // Range.createContextualFragment() would be useful here but is
                        // only relatively recently standardized and is not supported in
                        // some browsers (IE9, for one)
                        var el = document.createElement("div");
                        el.innerHTML = html;
                        var frag = document.createDocumentFragment(), node, lastNode;
                        while ( (node = el.firstChild) ) {
                            lastNode = frag.appendChild(node);
                        }
                        var firstNode = frag.firstChild;
                        range.insertNode(frag);

                        // Preserve the selection
                        if (lastNode) {
                            range = range.cloneRange();
                            range.setStartAfter(lastNode);
                            if (selectPastedContent) {
                                range.setStartBefore(firstNode);
                            } else {
                                range.collapse(true);
                            }
                            sel.removeAllRanges();
                            sel.addRange(range);
                        }
                    }
                } else if ( (sel = document.selection) && sel.type != "Control") {
                    // IE < 9
                    var originalRange = sel.createRange();
                    originalRange.collapse(true);
                    sel.createRange().pasteHTML(html);
                    if (selectPastedContent) {
                        range = sel.createRange();
                        range.setEndPoint("StartToStart", originalRange);
                        range.select();
                    }
                }
            },
            /**
             * 时间格式化
             * @param data Data()
             * @param format
             * @returns {*}
             */
            format(dataObj, format){
                var date = {
                    "M+": dataObj.getMonth() + 1,
                    "d+": dataObj.getDate(),
                    "h+": dataObj.getHours(),
                    "m+": dataObj.getMinutes(),
                    "s+": dataObj.getSeconds(),
                    "q+": Math.floor((dataObj.getMonth() + 3) / 3),
                    "S+": dataObj.getMilliseconds()
                };
                if (/(y+)/i.test(format)) {
                    format = format.replace(RegExp.$1, (dataObj.getFullYear() + '').substr(4 - RegExp.$1.length));
                }
                for (var k in date) {
                    if (new RegExp("(" + k + ")").test(format)) {
                        format = format.replace(RegExp.$1, RegExp.$1.length == 1
                                ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
                    }
                }
                return format;
            }
        };
        // chart-area
        $.tool.scrollEnd($('#chart-area'));

        var progress = {
            /**
             * 文件进度上传监听
             */
            listener: function (e) {
                if (e.lengthComputable) {
                    var percent = e.loaded/ e.total*100;
                    $("#progress-load").css("width", percent.toFixed(2) + "%");
                }
            },
            reset: function () {
                $("#progress-load").css("width", "0");
            },
            xhr: function () {
                //获取ajaxSettings中的xhr对象，为它的upload属性绑定progress事件的处理函数
                var xhr = $.ajaxSettings.xhr();
                if(xhr.upload){ //检查upload属性是否存在
                    //绑定progress事件的回调函数
                    xhr.upload.addEventListener('progress', progress.listener, false);
                }
                return xhr; //xhr对象返回给jQuery使用
            }
        };

        $('#picture').on("change", function () {
            var $this = $(this);
            var path = $this.val();
            var ext = path.substr(path.lastIndexOf('.') + 1);
            if (ext != 'jpg' && ext != 'gif'
                    && ext != 'png' && ext != 'bmp') {
                alert("请选择图片文件");
                return;
            }
            if(this.files[0].size > 10 * 1024 * 1024) {
                alert("请勿上传超过10M的文件");
                return;
            }
            $.ajax({
                type: "POST",
                url: "/f/upload",
                data: new FormData($("#picture-form")[0]),
                xhr: progress.xhr,
                cache: false,
                contentType: false,
                processData: false,
                success: function(data) {
                    if (data.code == 200) {
                        $.messageHandler.sendMessage("IMAGE", "/f/"+data.data);
                        alert("上传成功");
                        progress.reset();
                    }else {
                        alert(data.message);
                    }
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    alert("上传失败，请检查网络后重试");
                }
            });
        });
        $('#files').on("change", function () {
            var $this = $(this);
            var path = $this.val();
            if (!path) {
                alert("请选择文件");
                return;
            }
            if(this.files[0].size > 10 * 1024 * 1024) {
                alert("请勿上传超过10M的文件");
                return;
            }
            $.ajax({
                type: "POST",
                url: "/f/upload",
                data: new FormData($("#files-form")[0]),
                xhr: progress.xhr,
                cache: false,
                contentType: false,
                processData: false,
                success: function(data) {
                    if (data.code == 200) {
                        $.messageHandler.sendMessage("FILE", "/f/"+data.data);
                        alert("上传成功");
                        progress.reset();
                    }else {
                        alert(data.message);
                    }
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    alert("上传失败，请检查网络后重试");
                }
            });
        });
        // 键盘监听 ctrl+enter
        $(document).keydown(function(event){
            if (event.keyCode == 13 && event.ctrlKey) { // 发送按钮
                $("#send-btn").click();
            }
            if (event.keyCode == 8 && event.ctrlKey) { // 清空按钮
                $("#clean-btn").click();
            }
//            console.log(event.keyCode);
        });

    });
</script>
</body>
</html>
