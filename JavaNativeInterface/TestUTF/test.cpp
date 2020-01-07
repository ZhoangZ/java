#include <iostream>
#include <string>
#include <locale>

int main()
{
    using namespace std;

    std::locale::global(locale("en_US.utf8"));

    std::wstring s;

    std::wcin >> s;

    std::wcout << s;

}