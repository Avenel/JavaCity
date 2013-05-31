#version 330
precision highp float;

#define ATTR_POSITION  0
#define ATTR_NORMAL    1
#define ATTR_TEXCOORD0 2
#define ATTR_TEXCOORD1 3
#define ATTR_TEXCOORD2 4
#define ATTR_TEXCOORD3 5
#define ATTR_TEXCOORD4 6
#define ATTR_TEXCOORD5 7
#define ATTR_TEXCOORD6 8
#define ATTR_TEXCOORD7 9

layout(location = ATTR_POSITION)  in vec4 gs_Vertex;
layout(location = ATTR_NORMAL)    in vec3 gs_Normal;
layout(location = ATTR_TEXCOORD0) in vec4 gs_MultiTexCoord0;
layout(location = ATTR_TEXCOORD1) in vec4 gs_MultiTexCoord1;
layout(location = ATTR_TEXCOORD2) in vec4 gs_MultiTexCoord2;
layout(location = ATTR_TEXCOORD3) in vec4 gs_MultiTexCoord3;
layout(location = ATTR_TEXCOORD4) in vec4 gs_MultiTexCoord4;
layout(location = ATTR_TEXCOORD5) in vec4 gs_MultiTexCoord5;
layout(location = ATTR_TEXCOORD6) in vec4 gs_MultiTexCoord6;
layout(location = ATTR_TEXCOORD7) in vec4 gs_MultiTexCoord7;

out vec4 gs_TexCoord[8];

void main()
{	
    gl_Position    = gs_Vertex;
    gs_TexCoord[0] = gs_MultiTexCoord0;
}


