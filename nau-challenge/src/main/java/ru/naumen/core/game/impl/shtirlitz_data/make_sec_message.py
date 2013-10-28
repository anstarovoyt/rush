import os
from random import randint
from string import find, lower

# coding=utf-8

work_dir = '/Users/achernin/IdeaProjects/nau-challenge/nau-challenge/src/main/java/ru/naumen/core/game/impl/shtirlitz_data'

def file_with_dir(filename):
    return os.path.join(work_dir, filename)


def chapter_filename(chapter_number):
    return file_with_dir('anna_karenina_%s.txt' % str(chapter_number))


def split_to_chapters(filename):
    is_chapter = lambda non_empty_lines_counter: non_empty_lines_counter > 3

    chapter_number = 0
    chapter_buffer = list()
    chapter_buffer_len = 0

    def flush_buffer(chapter_number, chapter_buffer):
        chapter_file = open(chapter_filename(chapter_number), 'w')
        for line in chapter_buffer:
            chapter_file.write(line + '\n')
        chapter_file.close()

    f = open(file_with_dir(filename), 'r')

    for line in f:
        if line.endswith('\n'):
            line = line.rstrip('\n')
        line = line.strip()

        if not line:
            if is_chapter(chapter_buffer_len):
                flush_buffer(chapter_number, chapter_buffer)
                chapter_number += 1
            chapter_buffer = list()
            chapter_buffer_len = 0
        else:
            chapter_buffer.append(line)
            chapter_buffer_len += 1


def random_chapter_number():
    return randint(0, 240)

def work_format(line):
    line = line.decode('utf-8')
    line = lower(line)
    return line

def encript_msg(msg, chapter_number=None):
    if not chapter_number:
        chapter_number = random_chapter_number()

    filename = chapter_filename(chapter_number)
    f = open(filename, 'r')
    lines = f.readlines()
    f.close()

    def get_char_tuple(char, lines):
        line_num = 0
        num = -1
        for line in lines:
            line_num += 1
            line = work_format(line)
            num = find(line, char)
            if num > -1:
                break
        if num == -1:
            return None # can't encript with given chapter
        return (line_num, num + 1)

    criptogramma = []

    for ch in msg:
        tpl = get_char_tuple(ch, lines)
        if not tpl:
             return None
        criptogramma.append(tpl)

    return criptogramma

# split_to_chapters('anna_karenina.txt')

def get_msg_to_encript():
    filename = 'msg.txt'
    f = open(filename, 'r')
    line = f.readline()
    line = work_format(line)
    f.close()
    if line.endswith('\n'):
        line = line.rstrip('\n')
    return line

msg = get_msg_to_encript()
for i in range(0, 241):
    criptogramma = encript_msg(msg, i)
    if criptogramma:
        print i
        print criptogramma