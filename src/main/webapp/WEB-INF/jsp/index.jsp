<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC>
<html>

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src='<c:url value="/jQuery/js/jquery-3.1.1.js"/>'></script>
    <script src='<c:url value="/jQuery/js/jquery.dataTables.js"/>'></script>

    <script src='<c:url value="/bootstrap/js/bootstrap.min.js"/>'></script>
    <link rel="stylesheet" href='<c:url value="/bootstrap/css/bootstrap.min.css"/>'>

    <link rel="stylesheet" href='<c:url value="/jQuery/css/jquery.dataTables.css"/>'>
    <script>
        
        $(function(){
            
            $('#submitBt').click(function(){
                var allTables = getAllTables().toString();
                
                var data = $('#insertForm').serialize();
                
                data = data+'&tables='+allTables;
                
                $.post('<c:url value="/generateController/generate.do"/>',data,function(){
                    
                })
                
            })
            
            
        })
        
        function addRow(){
            $('#example').append(
                $('<tr>').append(
                    $('<th>') 
                ).append(
                    $('<td>').append(
                        $('<input>').attr('type','text').attr('title','rowNum')
                    ).append(
                        $('<button>').attr('name','rowNum').attr('type','button').click(function(){
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
            var rowS = $('input[title="rowNum"]');
            rowS.each(function(){
                var table = this.value;
                if($.isEmptyObject(table) == false){
                    allTables.push(this.value);    
                }                
            })
            return allTables;
        }

    </script>
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
    <title>Insert title here</title>
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
                        <input type='text' title='rowNum'/>
                        <button type='button' onclick='addRow()'>
                            <i class='glyphicon glyphicon-plus'></i>
                        </button>                        
                    </td>

                </tr>

            </table>
        </form>
    </div>    
</body>

</html>