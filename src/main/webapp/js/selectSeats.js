
class Seat{
    constructor(row, col, newStatus){
        this._row=row;
        this._col=col;
        this._status = newStatus;
    }
    set status(newStatus){
        this._status= newStatus;
    }
    get status(){
        return this._status;
    }
    get Position(){
        return [this._row,this._col] ;
    }
}
class SeatsRow{
    constructor(rowLength , rowNumber){
        this._rowLength= rowLength;
        this._rowNumber= rowNumber;
        this._seats= [];
        for( let i=1; i<=this._rowLength; i++){
            this._seats.push(new Seat(this._rowNumber, i,'unbooked'));
        }
    }
    get row_Length(){
        return this._rowLength;
    }
    get rowNumber(){
        return this._rowNumber;
    }
    seatOf(idx){
        return this._seats[idx];
    }
}

class threater{
    constructor(layout){
        this._layout = layout;
        this._seatRows = [];
        for( let [key , value] in this._layout){
            this._seatRows.push( new SeatsRow( this._layout[key],key) );
        }
    }
    rowOf(i){
        return this._seatRows[i];
    }
    get rows(){
        return this._seatRows;
    }
}