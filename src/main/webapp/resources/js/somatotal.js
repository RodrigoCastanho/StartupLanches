
var valorlanchepadrao = 0;
var valorlancheperson = 0; 
var total = 0;

$(document).ready(autosoma);


function autosoma() {

  valorlanchepadrao = $('#lanchepadrao').html(); 
  valorlancheperson = $('#lanchepersonalizado').html();

  total = (parseFloat(valorlanchepadrao) + parseFloat(valorlancheperson));
   
  $('#totaltudo').text(total.toFixed(2));

}	