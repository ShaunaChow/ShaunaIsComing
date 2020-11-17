var currentURL = '/';

function createXMLHttpRequest() {
    try{
        return new XMLHttpRequest();
    }catch (e) {
        try{
            return new ActiveXObject('Msxml2.XMLHTTP');
        }catch (e2) {
            throw e2;
        }
    }
}

function createItem(node) {
    console.log(node);
    var div = document.createElement('div');
    if (node.isFile == 0) {
        div.className = 'file';
    }else{
        div.className = 'dir';
    }

    var cover = document.createElement('div');
    cover.className = 'cover';
    div.appendChild(cover);

    var a1 = document.createElement('a');
    a1.href = '#';
    a1.className = 'name';
    a1.innerText = node.path;
    div.appendChild(a1);

    var a2 = document.createElement('a');
    a2.href = '/shaunafs/downloadtmp?filePath='+currentURL+node.path;
    a2.className = 'download';
    a2.innerText = '下载';
    div.appendChild(a2);

    var a3 = document.createElement('a');
    if (node.isFile == 0) {
        a3.href = '/shaunafs/rmfiletmp?filePath='+currentURL+node.path;
    }else{
        a3.href = '/shaunafs/rmdirtmp?filePath='+currentURL+node.path;
    }
    a3.className = 'delete';
    a3.innerText = '删除';
    div.appendChild(a3);

    var span = document.createElement('span');
    span.innerText = '-';
    div.appendChild(span);

    return div;
}

var items = document.querySelector('.items');

var request = createXMLHttpRequest();

request.open('GET','/shaunafs/getDir?path='+currentURL, true);

request.send(null);

request.onreadystatechange = function () {
    if (request.readyState == 4 && request.status == 200) {
        var text = request.responseText;
        var res = JSON.parse(text);
        console.log(res);
        if (res.code == 200) {
            var msg = res.msg;
            for(var i=0;i<msg.length;i++){
                var node = msg[i];
                items.insertBefore(createItem(node),items.children[0]);
            }
        }else {
            console.log('未知错误');
        }

    }
}
