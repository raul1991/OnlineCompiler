<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Code Syntax Highlighter Demo</title>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
        <link rel="stylesheet" type="text/css" media="all" href="css/global.css">

        <script src="ace-builds-master/src-min/ace.js" type="text/javascript" charset="utf-8"></script>
        <script src="ace-builds-master/src-noconflict/ace.js" type="text/javascript" charset="utf-8"></script>

        <script>
            var xm;lhttp;
            function request_creator(url,cfunc,data)
            {
                if (window.XMLHttpRequest)
                {// code for IE7+, Firefox, Chrome, Opera, Safari
                    xmlhttp=new XMLHttpRequest();
                }
                else
                {// code for IE6, IE5
                    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
                }
                xmlhttp.onreadystatechange=cfunc;
                xmlhttp.open("POST",url,true);
                xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
                xmlhttp.send("file="+data+"&type="+document.getElementById("type_of_file").value);

            }
            function submitTryit()
            {   //getLines();
                if(document.getElementById("type_of_file").value=="HTML"){
                    
                    alert("HTML");
                    var value=ace.edit("editor").getSession().getValue();
                    var iframeObject = window.frames["HOW_TO_FRAME"];
                    alert(iframeObject.window.document.getElementById("outputContainer"));
                    iframeObject.window.document.getElementById("outputContainer").innerHTML = value;
                    return;
                }

                var value=editor.getSession().getValue();   
                //                alert(value);
                request_creator("fun.do",function()
                {
     
                    //                    alert("message");
                    if (xmlhttp.readyState==4 && xmlhttp.status==200)
                    {   
                        
                        var value=ace.edit("editor").getSession().getValue();
                        var iframeObject = window.frames["HOW_TO_FRAME"];
                        
                        iframeObject.window.document.getElementById("outputContainer").innerHTML =xmlhttp.responseText;
                    }
                },encodeURIComponent(ace.edit("editor").getSession().getValue()));
            }


            function compileHTML () {
                var value=editor.getSession().getValue();
                var iframeObject = window.frames["HOW_TO_FRAME"];
                iframeObject.window.document.getElementById("outputContainer").innerHTML = value;
            }
            
            function getLines(){
                 var lines = editor.session.doc.getAllLines();
                var code="";
                for(i=0;i<lines.length;i++){
                    code+=lines[i];
                }
                alert(code);
            }
        </script>

    </head>

    <body>
        
        <table border="0" class="maintable">
            <tr>
                <td width="50%">


                    <select id="type_of_file">
                        <option>HTML</option>
                        <option>JAVA</option>
                    </select>
                    <input style="font-family:verdana;float:right;" type="button" value="Submit Code &raquo;" onclick="submitTryit()">
                    <div style="clear:both;"></div>

                </td>
                <td><div class="result_header" style="margin-top:8px;">Result:</div></td>
            </tr>
            <tr>
                <td valign="top">

                    <div id="editor" width="100%" height="400px"  wrap="logical">
                        <!DOCTYPE html>
                        <html>
                            <body>

                                <h1>My First Heading</h1>

                                <p>My first paragraph.</p>

                            </body>
                        </html>

                    </div>
                    <input type="hidden" name="code" id="code" />
                    <input type="hidden" id="bt" name="bt" />

                </td>
                <td valign="top">
                    <div id="viewIFRAME" class="result_output" width="100%" height="400px" >

                        <iframe  id="HOW_TO_FRAME" name="HOW_TO_FRAME" src="HowToWork.html" width="100%" height="400px"></iframe>

                    </div>

                </td>
            </tr>
            <tr>
                <td align="left" class="bottomtext">
                    Edit the code above and click "Submit Code" to see the result.
  
                </td>
                <td align="right" class="bottomtext">
                    <a style="color:#617f10" href="#">About the creator</a> - Try it yourself&nbsp;
                </td>
            </tr>
        </table>

        <script type="text/javascript">
            var editor = ace.edit("editor");
    
            editor.setTheme("ace/theme/dawn");
            editor.getSession().setTabSize(2);
            editor.getSession().setUseWrapMode(true);
    
            editor.getSession().setMode("ace/mode/java");
        </script>
    </body>
</html>