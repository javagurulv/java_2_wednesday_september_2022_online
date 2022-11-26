$(document).ready(function(){
            $(".ingredient").hide();
            $("#ingredient1").show();
            $("#ingredient1 input").attr('required', true);
            $(".cookingStep").hide();
            $("#step1").show();
            $("#step1 textarea").attr('required', true);
            var ingrCount = 2;
            var stepCount = 2;

            $("#addIngr").click(function(){
                        $("#ingredient" + ingrCount).show();
                        $("#ingredient" + ingrCount + " input").attr("required", true);
                        ingrCount++;
                    });

            $("#addStep").click(function(){
                        $("#step" + stepCount).show();
                        $("#step" + stepCount + " textarea").attr("required", true);
                        stepCount++;
                    });
        });
