# CarggoTestTask

### Острова

Учёные из института геодезии и картографии с помощью спутника сфотографировали
кусок тихого океана. Потом из неё составили карту островов. Но карта получилась
слишком большая, поэтому они решили обратиться к вам за помощью. Посчитайте число
островов по заданной карте.  
Остров — набор квадратных клеток суши, где из каждой клетки можно добраться до
любой другой. Остров полностью омывается водой. Острова не могут соприкасаться по
диагонали. На острове нет озёр, то есть внутри острова не может быть никакой воды.  

Вход: в первой строке через пробел записаны два числа N и M — высота и ширина карты
(1 ≤ N, M ≤ 5000). Далее идут N строк, по M символов каждая. `~` означает вода, `*` — суша.  

Например:  
```
7 6
~~~~~~
~*~**~
~***~~
~~~~~~
~*~~*~
~**~*~
~~~~~~
```   

Выход: Искомое число островов.  
Например: 3
