from glife import rect
import golly as g

"""
Как запустить этот скрипт:

 * открыть golly
 * нарисовать поле или открыть сохранённый паттерн
 * выделить участок поля
 * не снимая выделения, открыть этот скрипт из golly
"""

r = rect( g.getrect() )
if r.empty: g.exit("There is no pattern.")

with open('/tmp/dump.txt', 'w') as dump:
    for row in xrange(r.top, r.top + r.height):
        for col in xrange(r.left, r.left + r.width):
            s = g.getcell(col,row)
            dump.write('%d' % s)
        dump.write('\n')
