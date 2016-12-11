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
        fieldset{
            border:1px grey solid;
            padding-top:10px;
            padding-left:10px;
            margin-top:5px;
            margin-bottom: 5px;
        }
      
    </style>    
    <script>
        
        $(function(){
            
            $('#submitBt').click(function(){
                
                
                
                
                
                if(validConnectForm()){
                    

                    var data = $('#connectForm').serialize();

                    data = data+'&tables='+getAllTableForms().toString();
                    
                    var url = '/GenerateDBtoJava/generateController/generate.do';
                    
                    console.log(data);
                    
                    $.post(url,data,function(result){
                        alert(result.message);
                    })                    
                }else{
                    alert('required');
                };                
            })
            
            
            
            
            
//            // 頁面初始時            
//            var dbDriverClassNm = $('input[name=dbDriverClassNm]:checked', '#connectForm').val();
//            if($.isEmptyObject(dbDriverClassNm) == false){
//                setJdbcUrl(dbDriverClassNm);
//            }
//            // user 變更資料庫類型時
//            $('#connectForm input[name="dbDriverClassNm"]').change(function(){
//                dbDriverClassNm = $('input[name=dbDriverClassNm]:checked', '#connectForm').val();                
//                setJdbcUrl(dbDriverClassNm);
//            })
//            // host changed
//            $('#connectForm input[name="host"]').keyup(function(){
//                // jdbc:sqlserver://192.168.128.159:1433;databaseName=ssss
//                var jdbcUrl = $('#connectForm input[name="url"]').val();
//                var host = $(this).val();
//                if($.isEmptyObject(host)){
//                    host = '{host}';
//                }
//                var beforeHost = jdbcUrl.indexOf('//') + 2;
//                var afterHost = jdbcUrl.lastIndexOf(':');
//                var urlPrefix = jdbcUrl.substring(0,beforeHost);
//                var urlSuffix = jdbcUrl.substring(afterHost,jdbcUrl.length);
//                jdbcUrl = urlPrefix + host + urlSuffix;
//                $('#connectForm input[name="url"]').val(jdbcUrl)
//            })
//            // port changed
//            // TODO
//            $('#connectForm input[name="port"]').keyup(function(){
//                // jdbc:sqlserver://192.168.128.159:1433;databaseName=ssss
//                var jdbcUrl = $('#connectForm input[name="url"]').val();
//                var port = $(this).val();
//                var beforePort = jdbcUrl.lastIndexOf(':');
//                var afterPort = jdbcUrl.indexOf(';');
//                if(afterPort == -1){                    
//                    if($.isEmptyObject(port)){
//                        jdbcUrl = jdbcUrl.substring(0,beforePort)
//                    }else{
//                        jdbcUrl = jdbcUrl.substring(0,beforePort)+':'+port
//                    }
//                }else{
//                    if($.isEmptyObject(port)){
//                        jdbcUrl = jdbcUrl.substring(0,beforePort)+jdbcUrl.substring(afterPort,jdbcUrl.length)
//                    }else{
//                        jdbcUrl = jdbcUrl.substring(0,beforePort)+':'+port+jdbcUrl.substring(afterPort,jdbcUrl.length)
//                    }                    
//                }
//                $('#connectForm input[name="url"]').val(jdbcUrl);
//            })
//            // dbname changed
//            $('#connectForm input[name="dbName"]').keyup(function(){
//                var jdbcUrl = $('#connectForm input[name="url"]').val();
//                var dbName = $(this).val();          
//                var index = jdbcUrl.indexOf('=');
//                if(index == -1){                    
//                    jdbcUrl = jdbcUrl+';databaseName='+dbName;                    
//                }else{
//                    jdbcUrl = jdbcUrl.substring(0,index+1) + dbName;
//                }                
//                $('#connectForm input[name="url"]').val(jdbcUrl);
//            })
            
        })
        
//        function setJdbcUrl(dbDriverClassNm){            
//            $.get('/GenerateDBtoJava/generateController/getJdbcUrlPrefix.do',function(data){
//                $.each(data,function(index,map){
//                    $.each(map,function(db,defaultUrl){
//                        if(db == dbDriverClassNm){
//                            var urlPrefix = defaultUrl.split(',')[0];
//                            var defaultPort = defaultUrl.split(',')[1];
//                            // jdbc:sqlserver://192.168.128.159:1433;databaseName=ssss
//                            $('#connectForm input[name="url"]').val(urlPrefix+'localhost'+':'+defaultPort);
//                            $('#connectForm input[name="host"]').val('localhost');
//                            $('#connectForm input[name="port"]').val(defaultPort);
//                        }                        
//                    })
//                })
//            })            
//        }
        
            

        // add table input
        function addRow(obj){
            
//            <div class="form-group row">
//                <label class="col-xs-2 col-form-label"></label>
//                <div class="col-xs-2">
//                    <input class="form-control" type='text' title='table'/>
//                </div>
//                <button type='button'>
//                    <i class='glyphicon glyphicon-trash'></i>
//                </button>                
//            </div>            
            
            $(obj).parents('form:first').append(
                
                $('<div>').attr('class','form-group row').append(
                    $('<label>').attr('class','col-xs-2 col-form-label')                
                ).append(
                    $('<div>').attr('class','col-xs-5').append(
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
        // add fieldset
        $(function(){
            $('#addTableFormBt').click(function(){
//                <fieldset>
//                    <form name='tableForm'>
//                        <div class="form-group row">            
//                            <div class='col-xs-2'>
//                                <button type='button' class='btn btn-danger'>                        
//                                    <i class='glyphicon glyphicon-trash'></i>
//                                </button>
//                            </div>
//                        </div>                        
//                        <div class="form-group row">
//                            <label class="col-xs-2 col-form-label">DaoPackageRoot</label>
//                            <div class="col-xs-5">
//                                <input class="form-control" type='text' name='daoPackageRoot'/>
//                            </div>                
//                        </div>
//                        <div class="form-group row">
//                            <label class="col-xs-2 col-form-label">ModelPackageRoot</label>
//                            <div class="col-xs-5">
//                                <input class="form-control" type='text' name='modelPackageRoot'/>
//                            </div>                
//                        </div>            
//                        <div class="form-group row">
//                            <label class="col-xs-2 col-form-label">Table名稱</label>
//                            <div class="col-xs-5">
//                                <input class="form-control" type='text' title='table'/>
//                            </div>    
//                            <button type='button' class='btn btn-success' onclick='addRow(this)'>
//                                <i class='glyphicon glyphicon-plus'></i>
//                            </button>                
//                        </div>
//                    </form>
//                </fieldset>                
                
                $('div[class="mainDiv"]').append(
                
                    $('<fieldset>').append(
                    
                        $('<form>').attr('name','tableForm').append(
                        
                            $('<div>').attr('class','form-group row').append(
                                $('<div>').attr('class','col-xs-2').append(
                                    $('<button>').attr('type','button').attr('class','btn btn-danger').append(
                                        $('<i>').attr('class','glyphicon glyphicon-trash')
                                    ).click(function(){
                                        $(this).parents('fieldset:first').remove();
                                    })                                    
                                )
                            )
                        ).append(
                            $('<div>').attr('class','form-group row').append(
                                $('<label>').attr('class','col-xs-2 col-form-label').text('DaoPackageRoot')
                            ).append(
                                $('<div>').attr('class','col-xs-5').append(
                                    $('<input>').attr('class','form-control').attr('type','text').attr('name','daoPackageRoot')
                                )
                            )                       
                        ).append(
                            $('<div>').attr('class','form-group row').append(
                                $('<label>').attr('class','col-xs-2 col-form-label').text('ModelPackageRoot')
                            ).append(
                                $('<div>').attr('class','col-xs-5').append(
                                    $('<input>').attr('class','form-control').attr('type','text').attr('name','modelPackageRoot')
                                )
                            )
                        ).append(
                            $('<div>').attr('class','form-group row').append(
                                $('<label>').attr('class','col-xs-2 col-form-label').text('Table名稱')
                            ).append(
                                $('<div>').attr('class','col-xs-5').append(
                                    $('<input>').attr('class','form-control').attr('type','text').attr('title','table')
                                )                  
                            ).append(
                            
                                $('<button>').attr('type','button').attr('class','btn btn-success').attr('onclick','addRow(this)').append(
                                    $('<i>').attr('class','glyphicon glyphicon-plus')
                                )
                            
                            )
                        )
                    )
                
                
                );
            })
        })
        // get allTables
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
        //
        function validConnectForm(){
            var classNm = $('#connectForm input[name="dbDriverClassNm"]').val();
            var ormapping = $('#connectForm input[name="orMappingType"]').val();
            var host = $('#connectForm input[name="host"]').val();
            var dbName = $('#connectForm input[name="dbName"]').val();
            var location = $('#connectForm input[name="savedLocation"]').val();
            var nm = $('#connectForm input[name="userNm"]').val();
            var psd = $('#connectForm input[name="psd"]').val();

            
            var isEmpty = $.isEmptyObject(classNm) || $.isEmptyObject(ormapping) ||
                          $.isEmptyObject(host) || $.isEmptyObject(dbName) || 
                          $.isEmptyObject(location) || $.isEmptyObject(nm) || 
                          $.isEmptyObject(psd);
            

            if(isEmpty){
                return false;
            }
            
            return true;
        }
        
        //
        function getAllTableForms(){
            var tableInfo = [];
            var tableForms = $('form[name="tableForm"]');
            $.each(tableForms,function(index,tableForm){
                var roots = [];
                var daoPackageRoot = $(this).find('input[name="daoPackageRoot"]').val();
                var modelPackageRoot = $(this).find('input[name="modelPackageRoot"]').val();
                
                roots.push(daoPackageRoot);
                roots.push(modelPackageRoot);
                var tables = $(this).find('input[title="table"]');
                
                var tableNms = [];
                tables.each(function(){
                    var tableNm = this.value;   
                    tableNms.push(tableNm);
                })
                var aa = roots.toString();
                var bb = tableNms.toString();
                tableInfo.push(aa+':'+bb+';');
            })
            return tableInfo;
        }
    </script>

    
</head>

<body>
    
    <div class='mainDiv'>
        <!----------------------------------- 連線資訊 ----------------------------------->
        <fieldset>
            <form id='connectForm'>
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

    <!--
                        <label class="custom-control custom-radio">
                            <input class="custom-control-input" type='radio' name='dbDriverClassNm' value='mySql'/>
                            <span class="custom-control-indicator"></span>
                            <span class="custom-control-description">MySql</span>                        
                        </label>                    
    -->
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

    <!--
                        <label>
                            <input class="form-check-input" type='radio' name='orMappingType' value='hibernate'/>
                            <span class="custom-control-indicator"></span>
                            <span class="custom-control-description">Hibernate</span>                        
                        </label>                    
    -->
                    </div>                
                </div>   
                <div class="form-group row">
                    <label class="col-xs-2 col-form-label">JDBC URL</label>
                    <div class="col-xs-6">
                        <input class="form-control" type='text' name='url' readonly/>                   
                    </div>                
                </div>            
                <div class="form-group row">
                    <label class="col-xs-2 col-form-label">Host</label>
                    <div class="col-xs-2">
                        <input class="form-control" type='text' name='host' />
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

            </form>
        </fieldset>
        <!----------------------------------- -------- ----------------------------------->
        <div class="form-group row">            
            <div class='submitBtnDiv'>
                <button type='button' class='btn btn-primary' id='addTableFormBt'>                        
                    <i class='glyphicon glyphicon-plus'></i>
                </button>
            </div>
        </div>
        <!----------------------------------- table相關 ----------------------------------->
        <fieldset>
            <form name='tableForm'>                
                <div class="form-group row">
                    <label class="col-xs-2 col-form-label">DaoPackageRoot</label>
                    <div class="col-xs-5">
                        <input class="form-control" type='text' name='daoPackageRoot'/>
                    </div>                
                </div>
                <div class="form-group row">
                    <label class="col-xs-2 col-form-label">ModelPackageRoot</label>
                    <div class="col-xs-5">
                        <input class="form-control" type='text' name='modelPackageRoot'/>
                    </div>                
                </div>            
                <div class="form-group row">
                    <label class="col-xs-2 col-form-label">Table名稱</label>
                    <div class="col-xs-5">
                        <input class="form-control" type='text' title='table'/>
                    </div>    
                    <button type='button' class='btn btn-success' onclick='addRow(this)'>
                        <i class='glyphicon glyphicon-plus'></i>
                    </button>                
                </div>
            </form>
        </fieldset>  
    </div>
      
</body>

