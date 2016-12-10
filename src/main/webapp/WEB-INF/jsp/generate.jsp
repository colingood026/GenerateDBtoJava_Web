<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<head>
    
 
    <style>
    
        .dataTableClass{
            width:100%;
            border:1px black solid
        }
        .mainDiv{
            margin:10px
        }
        .submitBtnDiv{
            margin:10px
        }
      
    </style>    
    <script>
        
        $(function(){
            
            $('#submitBt').click(function(){
                if(valid()){
                    var allTables = getAllTables().toString();

                    var data = $('#insertForm').serialize();

                    data = data+'&tables='+allTables;
                    
                    var url = '/GenerateDBtoJava/generateController/generate.do';
                    
                    $.post(url,data,function(result){
                        alert(result.message);
                    })                    
                }else{
                    alert('required');
                };                
            })
            // 頁面初始時            
            var dbDriverClassNm = $('input[name=dbDriverClassNm]:checked', '#insertForm').val();
            if($.isEmptyObject(dbDriverClassNm) == false){
                setJdbcUrl(dbDriverClassNm);
            }
            // user 變更資料庫類型時
            $('#insertForm input[name="dbDriverClassNm"]').change(function(){
                dbDriverClassNm = $('input[name=dbDriverClassNm]:checked', '#insertForm').val();                
                setJdbcUrl(dbDriverClassNm);
            })
            // host changed
            $('#insertForm input[name="host"]').keyup(function(){
                // jdbc:sqlserver://192.168.128.159:1433;databaseName=ssss
                var jdbcUrl = $('#insertForm input[name="url"]').val();
                var host = $(this).val();
                if($.isEmptyObject(host)){
                    host = '{host}';
                }
                var beforeHost = jdbcUrl.indexOf('//') + 2;
                var afterHost = jdbcUrl.lastIndexOf(':');
                var urlPrefix = jdbcUrl.substring(0,beforeHost);
                var urlSuffix = jdbcUrl.substring(afterHost,jdbcUrl.length);
                jdbcUrl = urlPrefix + host + urlSuffix;
                $('#insertForm input[name="url"]').val(jdbcUrl)
            })
            // port changed
            // TODO
            $('#insertForm input[name="port"]').keyup(function(){
                // jdbc:sqlserver://192.168.128.159:1433;databaseName=ssss
                var jdbcUrl = $('#insertForm input[name="url"]').val();
                var port = $(this).val();
                var beforePort = jdbcUrl.lastIndexOf(':');
                var afterPort = jdbcUrl.indexOf(';');
                if(afterPort == -1){                    
                    if($.isEmptyObject(port)){
                        jdbcUrl = jdbcUrl.substring(0,beforePort)
                    }else{
                        jdbcUrl = jdbcUrl.substring(0,beforePort)+':'+port
                    }
                }else{
                    if($.isEmptyObject(port)){
                        jdbcUrl = jdbcUrl.substring(0,beforePort)+jdbcUrl.substring(afterPort,jdbcUrl.length)
                    }else{
                        jdbcUrl = jdbcUrl.substring(0,beforePort)+':'+port+jdbcUrl.substring(afterPort,jdbcUrl.length)
                    }                    
                }
                $('#insertForm input[name="url"]').val(jdbcUrl);
            })
            // dbname changed
            $('#insertForm input[name="dbName"]').keyup(function(){
                var jdbcUrl = $('#insertForm input[name="url"]').val();
                var dbName = $(this).val();          
                var index = jdbcUrl.indexOf('=');
                if(index == -1){                    
                    jdbcUrl = jdbcUrl+';databaseName='+dbName;                    
                }else{
                    jdbcUrl = jdbcUrl.substring(0,index+1) + dbName;
                }                
                $('#insertForm input[name="url"]').val(jdbcUrl);
            })
            
        })
        
        function setJdbcUrl(dbDriverClassNm){            
            $.get('/GenerateDBtoJava/generateController/getJdbcUrlPrefix.do',function(data){
                $.each(data,function(index,map){
                    $.each(map,function(db,defaultUrl){
                        if(db == dbDriverClassNm){
                            var urlPrefix = defaultUrl.split(',')[0];
                            var defaultPort = defaultUrl.split(',')[1];
                            // jdbc:sqlserver://192.168.128.159:1433;databaseName=ssss
                            $('#insertForm input[name="url"]').val(urlPrefix+'localhost'+':'+defaultPort);
                            $('#insertForm input[name="host"]').val('localhost');
                            $('#insertForm input[name="port"]').val(defaultPort);
                        }                        
                    })
                })
            })            
        }
        
            

        
        function addRow(){
            
//            <div class="form-group row">
//                <label class="col-xs-2 col-form-label"></label>
//                <div class="col-xs-2">
//                    <input class="form-control" type='text' title='table'/>
//                </div>
//                <button type='button' onclick='addRow()'>
//                    <i class='glyphicon glyphicon-trash'></i>
//                </button>                
//            </div>            
            
            $('#insertForm').append(
                
                $('<div>').attr('class','form-group row').append(
                    $('<label>').attr('class','col-xs-2 col-form-label')                
                ).append(
                    $('<div>').attr('class','col-xs-2').append(
                        $('<input>').attr('type','text').attr('title','table').attr('class','form-control')
                    )
                ).append(
                    $('<button>').attr('type','button').attr('class','btn btn-danger').click(function(){
                        $(this).parents('div[class="form-group row"]:first').remove();
                    }).append(
                        $('<i>').attr('class','glyphicon glyphicon-trash')
                    )                
                )
            );
            
        }
        
        function getAllTables(){
            var allTables = [];
            var rowS = $('input[title="table"]');
            rowS.each(function(){
                var table = this.value;
                if($.isEmptyObject(table) == false){
                    allTables.push(this.value);    
                }                
            })
            return allTables;
        }
        
        function valid(){
            var classNm = $('#insertForm input[name="dbDriverClassNm"]').val();
            var ormapping = $('#insertForm input[name="orMappingType"]').val();
            var host = $('#insertForm input[name="host"]').val();
            var dbName = $('#insertForm input[name="dbName"]').val();
            var location = $('#insertForm input[name="savedLocation"]').val();
            var nm = $('#insertForm input[name="userNm"]').val();
            var psd = $('#insertForm input[name="psd"]').val();
            var tables = $('#insertForm input[title="table"]');
            var daoPackageRoot = $('#insertForm input[title="daoPackageRoot"]');
            var modelPackageRoot = $('#insertForm input[title="modelPackageRoot"]');
            
            var isEmpty = $.isEmptyObject(classNm) || $.isEmptyObject(ormapping) ||
                          $.isEmptyObject(host) || $.isEmptyObject(dbName) || 
                          $.isEmptyObject(location) || $.isEmptyObject(nm) || 
                          $.isEmptyObject(psd) || $.isEmptyObject(daoPackageRoot) ||
                          $.isEmptyObject(modelPackageRoot);
            
            var isTableEmpty = true;
            tables.each(function(){
                var table = this.value;               
                if($.isEmptyObject(table) == false){
                    isTableEmpty = false;
                }
            })
            if(isEmpty || isTableEmpty){
                return false;
            }
            
            return true;
        }
    </script>

    
</head>

<body>
    
    <div class='mainDiv'>

        <form id='insertForm'>
            <div class="form-group row">            
                <div class='submitBtnDiv'>
                    <button type='button' class='btn btn-primary' id='submitBt'>                        
                        <span>Submit</span>
                    </button>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-xs-2 col-form-label">資料庫類型</label>
                <div class="col-xs-10">
                    <label class="custom-control custom-radio">
                        <input class="custom-control-input" type='radio' name='dbDriverClassNm' value='msSql' checked/>
                        <span class="custom-control-indicator"></span>
                        <span class="custom-control-description">MSSQL</span>                        
                    </label>

                    <label class="custom-control custom-radio">
                        <input class="custom-control-input" type='radio' name='dbDriverClassNm' value='mySql'/>
                        <span class="custom-control-indicator"></span>
                        <span class="custom-control-description">MySql</span>                        
                    </label>                    
                </div>                
            </div>
            <div class="form-group row">
                <label class="col-xs-2 col-form-label">OrMapping類型</label>
                <div class="col-xs-10">
                    <label>
                        <input class="form-check-input" type='radio' name='orMappingType' value='mybatis' checked/>

                        <span class="custom-control-indicator"></span>
                        <span class="custom-control-description">Mybatis</span>                        
                    </label>

                    <label>
                        <input class="form-check-input" type='radio' name='orMappingType' value='hibernate'/>
                        <span class="custom-control-indicator"></span>
                        <span class="custom-control-description">Hibernate</span>                        
                    </label>                    
                </div>                
            </div>   
            <div class="form-group row">
                <label class="col-xs-2 col-form-label">JDBC URL</label>
                <div class="col-xs-4">
                    <input class="form-control" type='text' name='url' readonly/>                   
                </div>                
            </div>            
            <div class="form-group row">
                <label class="col-xs-2 col-form-label">Host</label>
                <div class="col-xs-2">
                    <input class="form-control numberAndDotOnly" type='text' name='host' />
                </div>                
            </div>
            <div class="form-group row">
                <label class="col-xs-2 col-form-label">Port:</label>
                <div class="col-xs-1">
                    <input class="form-control numberOnly" type='text' name='port' />
                </div>                
            </div>
            <div class="form-group row">
                <label class="col-xs-2 col-form-label">Database/Schema</label>
                <div class="col-xs-3">
                    <input class="form-control" type='text' name='dbName' />
                </div>               
            </div>
            <div class="form-group row">
                <label class="col-xs-2 col-form-label">資料產出位置</label>
                <div class="col-xs-5">
                    <input class="form-control" type='text' style='width:500px' name='savedLocation'/>
                </div>               
            </div> 
            <div class="form-group row">
                <label class="col-xs-2 col-form-label">帳號</label>
                <div class="col-xs-2">
                    <input class="form-control" type='text' name='userNm' />
                </div>
            </div>
            <div class="form-group row">
                <label class="col-xs-2 col-form-label">密碼</label>
                <div class="col-xs-2">
                    <input class="form-control" type='text' name='psd'/>
                </div>                
            </div>
            <div class="form-group row">
                <label class="col-xs-2 col-form-label">DaoPackageRoot</label>
                <div class="col-xs-2">
                    <input class="form-control" type='text' name='daoPackageRoot'/>
                </div>                
            </div>
            <div class="form-group row">
                <label class="col-xs-2 col-form-label">ModelPackageRoot</label>
                <div class="col-xs-2">
                    <input class="form-control" type='text' name='modelPackageRoot'/>
                </div>                
            </div>            
            <div class="form-group row">
                <label class="col-xs-2 col-form-label">Table名稱</label>
                <div class="col-xs-2">
                    <input class="form-control" type='text' title='table'/>
                </div>    
                <button type='button' class='btn btn-success' onclick='addRow()'>
                    <i class='glyphicon glyphicon-plus'></i>
                </button>                
            </div>
        </form>
    </div>
      
</body>

