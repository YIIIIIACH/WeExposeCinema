<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>電影廳院空位</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div style="margin: 20px 20%">
     <h1>電影廳院空位</h1>
    <div id="modal" class="theater"></div>
    </div>
</body>
<script src="js/threaterSeats.js"></script>
<script>
	let seatLayout = { ${seatsString} };
	let bookedSeats = [ ${bookedSeats}];
    let modal = document.getElementById('modal');
    let modal_page={
    'modal': modal,
    'modal_content': [],// modal_content consits of string of html
    'threater': {},
    'toggle':function(){
        console.log( "toggle");
        if(modal.style.display=="none"){
            modal.style.display='';
        }
        else{
            modal.style.display= "none";
        }
    },
    'init':function(layout){
        this.threater = new threater(seatLayout);
    },
    'refreshUI':function(){
        modal.innerHTML="";
        let cnt =0;
        for( let tmp of this.threater.rows){
            this.addSeatRow( tmp , cnt);
            cnt++;
        }
    },
    'addSeatRow':function( SeatsRow ,lv){
        let tmp = `<div class="seatRow" >`;
        for(let i=0; i< SeatsRow.row_Length; i++){
            let addedSeat = SeatsRow.seatOf(i);
            
            if( addedSeat.status =='booked'){
                tmp+= `<div class="seat booked"  ></div>`;
            }
            else{
                tmp+= `<div class="seat unbooked" ></div>`;
            }
        }
        tmp+=`</div>`;
        modal_page.modal.innerHTML+= tmp;
    },
    'setBooked':function( bookedSeats){
        for( let i=0; i< bookedSeats.length; i++){
            let seatRow =this.threater.rows
            seatRow[ bookedSeats[i][0]-1].seatOf( bookedSeats[i][1]-1).status= "booked";
        }
    }
};
modal_page.init();
modal_page.setBooked(bookedSeats);
modal_page.refreshUI();


</script>
</html>
