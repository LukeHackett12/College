package main.Structures

class MessageTime {
    Date timeStart
    Integer messageNo
    // five sec timeout: TimeCategory.minus(new Date(), timeStart) < new TimeDuration(0, 0, 5, 0)

    MessageTime(Integer messageNo){
        timeStart = new Date()
        this.messageNo = messageNo
    }
}
