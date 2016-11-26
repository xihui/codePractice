$(document).ready(function(){
//var c=document.getElementById("myCanvas");
//var ctx=$("#myCanvas")[0].getContext("2d");
//ctx.fillStyle="#FF0000";
//ctx.fillRect(0,0,150,75);
 $("#myCanvas").css({
 "position":"absolute",
"top":"0px",
"left":"0px",
"width":800,
"height":600});


 $("#myButton").css({
"position":"absolute",
"top":"30px",
"left":"25px"});
 
 $("a").click(function() {
     alert("Hello world!");
   });

//Kinetic figure
      var stage = new Kinetic.Stage({
        container: 'myCanvas',
        width: 578,
        height: 200
      });

      var layer = new Kinetic.Layer();

      var rect = new Kinetic.Rect({
        x: 239,
        y: 75,
        width: 100,
        height: 50,
        fill: 'green',
        stroke: 'black',
        strokeWidth: 4,
        draggable: true
      });
	
	
      // add the shape to the layer
      layer.add(rect);

      // add the layer to the stage
      stage.add(layer);
      
      $("#myButton").click(function(){
      	rect.setFill(getRandomColor());
		layer.draw();
      });


      function getRandomColor() {
    	var letters = '0123456789ABCDEF'.split('');
    	var color = '#';
    	for (var i = 0; i < 6; i++ ) {
        	color += letters[Math.round(Math.random() * 15)];
    	}
    	return color;
	  }
});
