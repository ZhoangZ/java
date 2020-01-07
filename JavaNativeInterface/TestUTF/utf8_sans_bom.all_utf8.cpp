#include <stdexcept>        // std::runtime_error, std::exception
#include <stdlib.h>         // EXIT_SUCCESS, EXIT_FAILURE
#include <iostream>         // std::cout, std::cerr, std::endl
#include <string>           // std::string
#include <locale.h>
using namespace std;
 
bool throwX( string const& s ) { throw runtime_error( s ); }
bool hopefully( bool v ) { return v; }
 
string lineFrom( istream& stream )
{
    string result;
    getline( stream, result );
    hopefully( !stream.fail() )
        || throwX( "lineFrom: failed to read line" );
    return result;
}
 
int main()
{
    setlocale(LC_ALL, "vietnam");
    try
    {
        static char const       narrowText[]    = "Blåbærsyltetøy! 日本国 кошка!";
         
        cout << "Narrow text: " << narrowText << endl;
        cout << endl;
        cout << "What's your name? ";
        string const name = lineFrom( cin );
        cout << "Glad to meet you, " << name << "!" << endl;
        return EXIT_SUCCESS;
    }
    catch( exception const& x )
    {
        cerr << "!" << x.what() << endl;
    }
    return EXIT_FAILURE;
}