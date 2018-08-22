document.write('<script src="/Asset/jquery/jquery-3.3.1.min.js" type="text/javascript"></script>');

document.write('<link rel="stylesheet" href="/Asset/bootstrap-3.3.7/css/bootstrap.min.css" type="text/css"/>');
document.write('<script src="/Asset/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>');

document.write('<script src="/Asset/bootstrap-table/bootstrap-table.js"></script>');
document.write('<link href="/Asset/bootstrap-table/bootstrap-table.css" rel="stylesheet">');
document.write('<script src="/Asset/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>');

document.write('<link rel="stylesheet" href="/Asset/toastr/css/toastr.min.css" type="text/css"/>');
document.write('<script src="/Asset/toastr/js/toastr.min.js" type="text/javascript"></script>');

document.write('<link rel="stylesheet" href="/Asset/SweetAlert/sweet-alert.css" type="text/css"/>');
document.write('<script src="/Asset/SweetAlert/sweet-alert.min.js" type="text/javascript"></script>');

document.write('<link rel="stylesheet" href="/Asset/font-awesome-4.7.0/css/font-awesome.min.css"　type="text/css" />');

document.write('<script src="/Asset/bootstrap-treeview/bootstrap-treeview.min.js" type="text/javascript"></script>');

document.write('<link rel="stylesheet" type="text/css" href="/Asset/bootstrap-duallistbox/prettify.css">');
document.write('<link rel="stylesheet" type="text/css" href="/Asset/bootstrap-duallistbox/bootstrap-duallistbox.css">');
document.write('<script src="/Asset/bootstrap-duallistbox/jquery.bootstrap-duallistbox.js"></script>');

document.write('<link rel="stylesheet" href="/Asset/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css"/>');
document.write('<script src="/Asset/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>');
document.write('<script src="/Asset/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.zh-CN.js" type="text/javascript" ></script>');

//转换日期格式(时间戳转换为datetime格式)
function changeDateFormat(cellval,format) {
    var dateVal = cellval + "";
    if (cellval != null) {
        var date = new Date(parseInt(dateVal.replace("/Date(", "").replace(")/", ""), 10));
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();

        var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
        var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
        var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();

        if (format == "yyyy-mm-dd"){
            return date.getFullYear() + "-" + month + "-" + currentDate;
        }else {
            return date.getFullYear() + "-" + month + "-" + currentDate + " " + hours + ":" + minutes + ":" + seconds;
        }
    }
}