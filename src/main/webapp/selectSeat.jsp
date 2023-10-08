<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>threat seats</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
     <h1>Seats layout</h1>
    <div id="modal" class="modal"></div>
    
</body>
<script src="js/selectSeats.js"></script>
<script>
	let seatLayout = { ${seatsString} };
	let bookedSeats = [ ${bookedSeats}];
    let modal = document.getElementById('modal');
    var modal_page={
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
        modal_page.modal.innerHTML="";
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
            else if(addedSeat.status=='unbooked'){
                tmp+= `<div class="seat unbooked" ></div>`;
            }
            else if( addedSeat.status=='selected'){
            	tmp+= `<div class="seat" style="background-color: rgb(237, 212, 0);border: 3px solid rgb(230, 240, 194)"></div>`;
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
    },
    'setSeatEventListener':function(){
        let rowIt = this.modal.firstChild;
        for( let row=1; row<= this.threater.rows.length; row++){
            let colIt = rowIt.firstChild;
            for( let col=1; col<= this.threater.rows[row-1].row_Length; col++){
                colIt.addEventListener('click' ,(event)=>{
                    let st= this.threater.rows[row-1].seatOf( col-1)
                    if(st.status== 'booked'){
                    	// if seat was booked by others do nothing
                    	;
                    }else if( st.status == 'unbooked'){
                    	st.status = 'selected';
                    }
                    else if ( st.status== 'selected'){
                    	st.status = 'unbooked';
                    	
                    }
                    modal_page.refreshUI();
                    modal_page.setSeatEventListener();
                });
                colIt= colIt.nextSibling;
            }
            rowIt = rowIt.nextSibling;
        }
    }
};
modal_page.init();
modal_page.setBooked(bookedSeats);
modal_page.refreshUI();
modal_page.setSeatEventListener();

</script>
</html>