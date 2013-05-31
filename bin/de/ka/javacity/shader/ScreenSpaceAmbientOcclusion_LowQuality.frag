#version 330

#define texture2D texture
#define textureCube texture

precision highp float;


in  vec4 gs_TexCoord[8];
out vec4 gs_FragColor;

uniform sampler2D ScreenTexture;
uniform sampler2D NormalTexture;
uniform sampler2D SceneCameraSpacePosAndDepthTexture;

uniform mat4 matViewProjection;

uniform float TextureSurfaceHeightScale;


uniform float OcclusionSampleStepDistance;
uniform float OcclusionInvDistanceFactor;
uniform float OcclusionIntensity;
uniform float OcclusionAmbientIntensity;
uniform float OcclusionBias;
uniform float InvSurfaceHeightOcclusionFactor;



float MaxCalculationRange = 0.1;


vec4 NormalColor;
vec4 CameraSpacePosAndDepth;


int NUM_STEPS_PER_DIRECTION = 4;

const vec2 Dir[36] = vec2[36](vec2(0.9848, 0.1736), vec2(0.9397, 0.342),
                        vec2(0.866, 0.5), vec2(0.766, 0.6428),
                        vec2(0.6428, 0.766), vec2(0.5, 0.866), 
                        vec2(0.342, 0.9397), vec2(0.1736, 0.9848),
                        vec2(0.0, 1.0),

                        vec2(-0.1736, 0.9848), vec2(-0.342, 0.9397),
                        vec2(-0.5, 0.866), vec2(-0.6428, 0.766),
                        vec2(-0.766, 0.6428), vec2(-0.866, 0.5),
                        vec2(-0.9397, 0.342), vec2(-0.9848, 0.1736),
                        vec2(-1.0, 0.0),
                        
                        vec2(-0.9848, -0.1736), vec2(-0.9397, -0.342),
                        vec2(-0.866, -0.5), vec2(-0.766, -0.6428),
                        vec2(-0.6428, -0.766), vec2(-0.5, -0.866),
                        vec2(-0.342, -0.9397), vec2(-0.1736, -0.9848),
                        vec2(0.0, -1.0),
                        
                        vec2(0.1736, -0.9848), vec2(0.342, -0.9397),
                        vec2(0.5, -0.866), vec2(0.6428, -0.766),
                        vec2(0.766, -0.6428), vec2(0.866, -0.5),
                        vec2(0.9397, -0.342), vec2(0.9848, -0.1736),
                        vec2(1.0, 0.0));



// 3D-Oberflächen-Wirkung samt Occlusion-Effekt berechnen:

void CalculateSurfaceHeightOcclusion(void)
{
    vec3 Normal = 2.0*NormalColor.rgb-vec3(1.0,1.0,1.0); 

    vec3 NormalizedCameraSpacePos = normalize(CameraSpacePosAndDepth.xyz);

    float MaxCalculationStep = 10.0*MaxCalculationRange;
    float ActualCalculationStep = 0.5*MaxCalculationStep;

    vec3 TestCameraSpacePos = CameraSpacePosAndDepth.xyz-ActualCalculationStep*NormalizedCameraSpacePos;
 
    vec4 Projection, TestNormalColor;
    float InvW, TexX, TexY, TestHeight, tempFloat;
    vec3 TestNormal;

    for(int i = 0; i < 10; i++)
    {
        Projection = matViewProjection*vec4(TestCameraSpacePos, 0.0);

        InvW = 1.0/Projection.w;
        TexY = 0.5*Projection.y*InvW+0.5;
        TexX = 0.5*Projection.x*InvW+0.5;

        TestNormalColor = texture2D(NormalTexture, vec2(TexX, TexY));

        TestNormal = 2.0*TestNormalColor.rgb-vec3(1.0,1.0,1.0);
            
        TestHeight = NormalColor.a;

        tempFloat = TextureSurfaceHeightScale*dot(TestCameraSpacePos-CameraSpacePosAndDepth.xyz, TestNormal);

        ActualCalculationStep *= 0.5;

        if(tempFloat < TestHeight)
        {
            TestCameraSpacePos = TestCameraSpacePos - ActualCalculationStep*NormalizedCameraSpacePos;
        }
        else
        {
            TestCameraSpacePos = TestCameraSpacePos + ActualCalculationStep*NormalizedCameraSpacePos;
        }
    }
 
    //gs_FragColor = texture2D(ScreenTexture, gs_TexCoord[0].st);

    gs_FragColor = max(length(TestCameraSpacePos-CameraSpacePosAndDepth.xyz),-InvSurfaceHeightOcclusionFactor*dot(TestNormal,NormalizedCameraSpacePos))*texture2D(ScreenTexture,  gs_TexCoord[0].st);

    //gs_FragColor = length(TestCameraSpacePos-CameraSpacePosAndDepth.xyz)*texture2D(ScreenTexture, gs_TexCoord[0].st);
}




void CalculateDistanceBasedOcclusion(void)
{
   
    vec3 Normal = 2.0*NormalColor.rgb-vec3(1.0,1.0,1.0);  

    int i, j;

    vec2 ScreenSpaceSamplePos;
    vec2 ScreenSpaceSampleDirection;

    vec4 CameraSpaceSamplePosAndDepth;

    vec3 CameraSpaceSampleDirection;

    float deltaTex;

    float distance;

    float Occlusion = 0.0;

        

    //float AmbientOcclusionScale = OcclusionIntensity/float(36*NUM_STEPS_PER_DIRECTION);
    //float AmbientOcclusionScale = OcclusionIntensity/float(18*NUM_STEPS_PER_DIRECTION);
    float AmbientOcclusionScale = OcclusionIntensity/float(12*NUM_STEPS_PER_DIRECTION);


    //for(i = 0; i < 36; i++) // 36 Richtungen

    //for(i = 0; i < 36; i+=2) // 18 Richtungen
    
    for(i = 0; i < 36; i+=3) // 12 Richtungen
    {
        ScreenSpaceSampleDirection = Dir[i];

        ScreenSpaceSamplePos = gs_TexCoord[0].st;

        for(j = 0; j < NUM_STEPS_PER_DIRECTION; j++)
        {
            ScreenSpaceSamplePos += OcclusionSampleStepDistance*ScreenSpaceSampleDirection;

            CameraSpaceSamplePosAndDepth = texture2D(SceneCameraSpacePosAndDepthTexture, ScreenSpaceSamplePos);

            if(CameraSpaceSamplePosAndDepth.a > 0.0)
            {
                CameraSpaceSampleDirection = CameraSpaceSamplePosAndDepth.xyz-CameraSpacePosAndDepth.xyz;
               
                distance = length(CameraSpaceSampleDirection);

                CameraSpaceSampleDirection = (1.0/distance)*CameraSpaceSampleDirection;

                Occlusion += AmbientOcclusionScale*max( 0.0, dot( Normal, CameraSpaceSampleDirection)+OcclusionBias) * ( 1.0 / ( 1.0 + OcclusionInvDistanceFactor*distance ) );
                
            } 
        }
    }

    //gs_FragColor = OcclusionAmbientIntensity*max(0.0,(1.0-Occlusion))*vec4(1.0, 1.0, 1.0, 1.0);

    gs_FragColor = OcclusionAmbientIntensity*max(0.0,(1.0-Occlusion))*texture2D(ScreenTexture, gs_TexCoord[0].st);

    //gs_FragColor = texture2D(ScreenTexture, gs_TexCoord[0].st);
}


void main()
{ 
    NormalColor = texture2D(NormalTexture, gs_TexCoord[0].st);

    if(NormalColor.r < 0.01 && NormalColor.g < 0.01 && NormalColor.b < 0.01)
    {
        gs_FragColor = texture2D(ScreenTexture, gs_TexCoord[0].st);
    }
    else
    {
        CameraSpacePosAndDepth = texture2D(SceneCameraSpacePosAndDepthTexture, gs_TexCoord[0].st);

        if(NormalColor.a > 0.0)
           CalculateSurfaceHeightOcclusion();
        else
            CalculateDistanceBasedOcclusion();
       
    }        
}


