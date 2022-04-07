#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int cmps(char* str1, char* str2){
    int s1 = strlen(str1);
    int s2 = strlen(str2);
    if(s1 != s2) return 1;
    for(int i = 0; i < s1; i++){
        if(str1[i] != str2[i])
            return 2;
    }
    return 0;
}

int main(int argc, char** argv, char** envv){
    if(argc < 2) return 0;
    char* arg = argv[1];
    char* c = "{3dNk3d0kw2d93lD3sM}";
    if(cmps(arg, c) == 0){
        printf("Félicitations, vous avez trouvé le flag !\n");
    }
    return 0;
}