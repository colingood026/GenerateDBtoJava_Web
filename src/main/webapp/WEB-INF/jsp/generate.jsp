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
                    
                    $.post(url,data,function(){

                    })                    
                }else{
                    alert('required');
                };

                
            })
            //

            
        })
        
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
            var url = $('#insertForm input[name="url"]').val();
            var location = $('#insertForm input[name="savedLocation"]').val();
            var nm = $('#insertForm input[name="userNm"]').val();
            var psd = $('#insertForm input[name="psd"]').val();
            var tables = $('#insertForm input[title="table"]');
            
            var isEmpty = $.isEmptyObject(classNm) || $.isEmptyObject(ormapping) ||
                          $.isEmptyObject(url) || $.isEmptyObject(location) || 
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
                            <input type='radio' name='dbType' value='mySql'/>
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
                    <th>資料庫URL</th>
                    <td colspan='4'>
                        <input type='text' style='width:500px' name='url'/>
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

