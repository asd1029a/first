const common = {
    ajax : (param, url, type, callback) => {
        $.ajax({
            url : url,
            type : type,
            data : JSON.stringify(param),
            dataType : "json",
            contentType : "application/json; UTF-8;"
        })
    }
}