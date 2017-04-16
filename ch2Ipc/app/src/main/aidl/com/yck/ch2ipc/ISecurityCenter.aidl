package com.yck.ch2ipc.binderpool;

interface ISecurityCenter {
    String encrypt(String content);
    String decrypt(String password);
}