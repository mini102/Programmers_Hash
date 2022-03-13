def solution(phone_book):
    answer = True
    
    phone_book.sort()
    
    #접두어!
    for index,i in enumerate(phone_book):
        if index != len(phone_book)-1 and phone_book[index+1].startswith(i):
            answer = False
            
    return answer
