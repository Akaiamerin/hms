$(document).ready(() => {
    if (document.querySelector('.preloader')) {
        $('.preloader').fadeOut(1000);
    }
    const metismenu = document.querySelector('#metismenu');
    if (metismenu) {
        $('#metismenu').metisMenu();
    }
    const table = document.querySelector('#dataTable');
    if (table) {
        $('#dataTable').dataTable({
            'dom': '<"row"f>t<"row"<"col-sm-12 col-md-3"li><"col-sm-12 col-md-3"><"col-sm-12 col-md-6"p>>',
            'language': {
                'decimal': '',
                'emptyTable': '',
                'info': '从 _START_ 到 _END_ 共 _TOTAL_ 条数据',
                'infoEmpty': '从 0 到 0 共 0 条数据',
                'infoFiltered': '（从 _MAX_ 条数据中检索）',
                'infoPostFix': '',
                'thousands': ',',
                'lengthMenu': '每页显示 _MENU_ 条数据',
                'loadingRecords': '数据加载中······',
                'processing': '数据加载中······',
                'search': '搜索',
                'zeroRecords': '',
                'paginate': {
                    'first': '首页',
                    'last': '尾页',
                    'next': '下一页',
                    'previous': '上一页'
                },
                'aria': {
                    'sortAscending': ': 升序',
                    'sortDescending': ': 降序'
                }
            },
        });
    }
    const loginBtn = document.querySelector('#loginBtn');
    if (loginBtn) {
        loginBtn.addEventListener(
            'click',
            () => {
                $('#username').val(`${$('.form-check-input[type="radio"]:checked').val()}&${$('#username').val()}`);
            }
        )
    }
    const uploadPicture = document.querySelector('#uploadPicture');
    if (uploadPicture) {
        uploadPicture.addEventListener(
            'change',
            () => {
                const fr = new FileReader();
                fr.readAsDataURL(uploadPicture.files[0]);
                fr.addEventListener(
                    'load',
                    (event) => {
                        $('#picture').val(event.target.result);
                        document.getElementById('picture').value = event.target.result;
                    }
                );
            }
        );
    }
});