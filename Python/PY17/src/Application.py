#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Time    : 16/05/2018 9:32 AM
# @Author  :
# @Site    : 
# @File    : Application.py
# @Software: PyCharm
import matplotlib.pyplot as plt
import numpy as np
earth_r=6371
close_floor=200
far_floor=350
def draw_oval(a,b):
    fig=plt.figure(figsize=(6,6))
    ax=fig.add_subplot(111)

    theta=np.arange(0,2*np.pi,np.pi/100)
    x=a*np.cos(theta)
    y=b*np.sin(theta)
    ax.plot(x,y)
    ax.set_xlim([-10000,10000])
    ax.set_ylim([-10000,10000])
    plt.show()
print("Assume the equation is x^2/a^2+y^2/b^2=1")
a_div_c=earth_r+close_floor
print("a-c=|OA|-|OF2|="+str(a_div_c))
a_plus_c=earth_r+far_floor
print("a+c=|OB|+|OF2|="+str(a_plus_c))
a=6646
c=75
print("Solutions is a=6646 c=75,so a^2=44169316")
print("b^2=a^2-c^2=(a+c)*(a-c)="+str(a_plus_c*a_div_c))
b=6645.6
draw_oval(a,b)