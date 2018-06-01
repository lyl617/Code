#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Time    : 12/05/2018 12:35 PM
# @Author  : Jsen617
# @Site    : 
# @File    : test.py
# @Software: PyCharm
import random
a={"1":1,"2":2,"3":3}
print(list(a))
a=random.sample(list(a),1)
print(type(a))