def solution(participant, completion):
    answer = ''
    plist = {}
    clist = {}
    sump = 0
    sumc = 0 
    for i in participant:
        sump += hash(i)
        plist[hash(i)] = i

    
    for i in completion:
        sumc+= hash(i)
        clist[hash(i)] = i
    
    
    index = sump-sumc
    # print("index: {}".format(index))
    
    answer = plist[index]
    
    return answer
