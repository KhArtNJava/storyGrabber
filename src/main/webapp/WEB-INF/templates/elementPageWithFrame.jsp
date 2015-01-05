<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <body style="margin: 0px;">

        <iframe style="border: none;" src="${iframeUrl}" width="100%" height="100%"></iframe>

        <pre style="width: 700px; word-wrap: break-word;">
                <div id="qqq">
                ${codeStr}    
                </div>
        </pre>


        <script>
            String.prototype.escapeHTML = function () {
                return(
                        this.replace(/>/g, '&gt;').
                        replace(/</g, '&lt;').
                        replace(/"/g, '&quot;')
                        );
            };
            var codeEl = document.getElementById('qqq');
            if (codeEl) {
                codeEl.innerHTML = codeEl.innerHTML.escapeHTML();
//                alert(codeEl.innerHTML);
            }
        </script>

    </body>
</html>
