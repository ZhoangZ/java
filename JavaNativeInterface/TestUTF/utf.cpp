#include <cstdio>
#include <iostream>
#include <windows.h>
#include <locale.h>
#define MAX_INPUT_LENGTH 255
using namespace std;
int main()
{
    // Getting the readable Cyrillic chars in the console window...
    setlocale(LC_ALL, "vietnam");
    char HT[50];
    cout<<"Họ tên:";
    cin>>HT;
    wcout << endl << "Tấn"; // UNICODE
    
    cout << HT << endl; // ANSI
    return 0;
}