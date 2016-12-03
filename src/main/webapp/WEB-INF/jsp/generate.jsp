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
            $('#example').append(
                $('<tr>').append(
                    $('<th>') 
                ).append(
                    $('<td>').append(
                        $('<input>').attr('type','text').attr('title','table')
                    ).append(
                        $('<button>').attr('type','button').click(function(){
                            $(this).parents('tr:first').remove();
                        }).append(
                            $('<i>').attr('class','glyphicon glyphicon-trash')
                        )                    
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
            var port = $('#insertForm input[name="port"]').val();
            var dbName = $('#insertForm input[name="dbName"]').val();
            var location = $('#insertForm input[name="savedLocation"]').val();
            var nm = $('#insertForm input[name="userNm"]').val();
            var psd = $('#insertForm input[name="psd"]').val();
            var tables = $('#insertForm input[title="table"]');
            
            var isEmpty = $.isEmptyObject(classNm) || $.isEmptyObject(ormapping) ||
                          $.isEmptyObject(host) || $.isEmptyObject(port) ||
                          $.isEmptyObject(dbName) || $.isEmptyObject(location) || 
                          $.isEmptyObject(nm) || $.isEmptyObject(psd);
            
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
        <div class='submitBtnDiv'>
            <button type='button' class='btn btn-primary' id='submitBt'>
                Submit
            </button>
        </div>
        <form id='insertForm'>
            <table id="example" class="table" cellspacing="0">
                <tr>
                    <th>資料庫類型</th>
                    <td>
                        <label>
                            <input type='radio' name='dbDriverClassNm' value='msSql' checked/>
                            &nbsp;<span>MSSQL</span>
                        </label>
<!--
                        &nbsp;&nbsp;
                        <label>
                            <input type='radio' name='dbDriverClassNm' value='mySql'/>
                            &nbsp;<span>MySql</span>                            
                        </label>
-->
                    </td>                
                </tr>
                <tr>
                    <th>OrMapping類型</th>
                    <td>
                        <label>
                            <input type='radio' name='orMappingType' value='mybatis' checked/>
                            &nbsp;<span>Mybatis</span>
                        </label>
                        &nbsp;&nbsp;
                        <label>
                            <input type='radio' name='orMappingType' value='hibernate'/>
                            &nbsp;<span>Hibernate</span>                            
                        </label>

                    </td>                
                </tr>                
                <tr>
                    <th>JDBC URL</th>
                    <td colspan='4'>
                        <input type='text' style='width:500px' name='url' readonly/>
                    </td>                
                </tr>
                <tr>
                    <th>Host</th>
                    <td >
                        <input type='text' style='width:200px' name='host' class="numberAndDotOnly"/>
                        &nbsp;&nbsp;<span style="font-weight:bold;">Port:</span>
                        <input type='text' style='width:100px' name='port' class="numberOnly"/>
                    </td>                    
                </tr>
                <tr>
                    <th>Database/Schema</th>
                    <td >
                        <input type='text' style='width:200px' name='dbName' />
                    </td>                   
                </tr>                
                <tr>
                    <th>資料產出位置</th>
                    <td colspan='4'>
                        <input type='text' style='width:500px' name='savedLocation'/>
                    </td>                
                </tr>                
                <tr>
                    <th>帳號</th>
                    <td>
                        <input type='text' name='userNm' />
                    </td>
                    <th>密碼</th>
                    <td>
                        <input type='text' name='psd'/>
                    </td>                
                
                </tr>
                <tr>
                    <th>Table名稱</th>
                    <td>
                        <input type='text' title='table'/>
                        <button type='button' onclick='addRow()'>
                            <i class='glyphicon glyphicon-plus'></i>
                        </button>                        
                    </td>

                </tr>

            </table>
        </form>
    </div>
      
</body>

