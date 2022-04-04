import re

fileName = input('Enter file name: ')
inputQuestion = input('Enter question: ')
inputQuestionAtom = input('Enter question atom: ')
answerArray = []
answerAtomsString = ''

run = int(1)
while run != 0:
    inputAnswer = input('Enter answer: ')
    inputAnswerAtom = input('Enter answer atom: ')
    answerArray.append((inputAnswer, inputAnswerAtom))
    userAct = int(input('ADD ANOTHER ONE [Any], EXIT [0]\nanswer:> '))
    run = userAct

newPlFile = open('database/' + fileName + '.pl', 'w')
fileString = '/* Question */\n/* Answers */\n/* Knowledge */'

questionStructure = 'question(' + inputQuestionAtom + '):-\n\twrite(\'' + inputQuestion + '\').\n'
fileString = re.sub(r'(\n)(\/\* Answers \*\/)', r'\1' + questionStructure + r'\2', fileString)

answerAtomsString = answerArray[0][1]
for i in range(1, len(answerArray)):
    answerAtomsString += ', ' + answerArray[i][1]

for i in answerArray:
    answerStructure = 'answer(' + i[1] + '):-\n' + '\twrite(\'' + i[0] + '\').\n'
    fileString = re.sub(r'(\n)(\/\* Knowledge \*\/)', r'\1' + answerStructure + r'\2', fileString)

knowledgeStructure = inputQuestionAtom + '(Answer):-\n\twrite(' + \
    inputQuestionAtom + ', Answer).\n' + inputQuestionAtom + '(Answer):-\n\t\+ progress(' + inputQuestionAtom + \
    ',_),\n\task(' + inputQuestionAtom + ', Answer, [' + answerAtomsString + ']).\n'
fileString = fileString + '\n' + knowledgeStructure

newPlFile.write(fileString)
newPlFile.close()


''' ADD FILE TO INCLUDE PATH '''

incFile = open('database/include.pl', 'a')
incFile.write('\n:- ensure_loaded(\'' + fileName + '.pl\').')
incFile.close()