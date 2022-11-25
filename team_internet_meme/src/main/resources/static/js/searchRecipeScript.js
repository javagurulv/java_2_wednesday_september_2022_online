$(document).ready(function(){
            $(".ingredient").hide();
            $("#ingredient1").show();
            $("#ingredient1 input").attr('required', true);
            var ingrCount = 2;

            $("#addIngr").click(function(){
                        $("#ingredient" + ingrCount).show();
                        $("#ingredient" + ingrCount + " input").attr("required", true);
                        ingrCount++;
                    });
        });