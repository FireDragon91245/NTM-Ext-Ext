package com.hbm.render.util;

import com.hbm.render.amlfrom1710.CompositeBrush;
import com.hbm.render.amlfrom1710.TextureCoordinate;
import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.render.amlfrom1710.Vertex;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HbmFace {
    public Vertex[] vertices;
    public Vertex[] vertexNormals;
    public Vertex faceNormal;
    public TextureCoordinate[] textureCoordinates;

    @SideOnly(Side.CLIENT)
    public void addFaceForRender(CompositeBrush tessellator)
    {
        addFaceForRender(tessellator, 0.0005F);
    }

    @SideOnly(Side.CLIENT)
    public void addFaceForRender(CompositeBrush tessellator,float textureOffset)
    {
        if (faceNormal == null)
        {
            faceNormal = this.calculateFaceNormal();
        }

        tessellator.setNormal(faceNormal.x, faceNormal.y, faceNormal.z);

        float averageU = 0F;
        float averageV = 0F;

        if ((textureCoordinates != null) && (textureCoordinates.length > 0))
        {
            for (int i = 0; i < textureCoordinates.length; ++i)
            {
                averageU += textureCoordinates[i].u;
                averageV += textureCoordinates[i].v;
            }

            averageU = averageU / textureCoordinates.length;
            averageV = averageV / textureCoordinates.length;
        }

        float offsetU, offsetV;

        for (int i = 0; i < vertices.length; ++i)
        {

            if ((textureCoordinates != null) && (textureCoordinates.length > 0))
            {
                offsetU = textureOffset;
                offsetV = textureOffset;

                if (textureCoordinates[i].u > averageU)
                {
                    offsetU = -offsetU;
                }
                if (textureCoordinates[i].v > averageV)
                {
                    offsetV = -offsetV;
                }

                tessellator.addVertexWithUV(vertices[i].x, vertices[i].y, vertices[i].z, textureCoordinates[i].u + offsetU, textureCoordinates[i].v + offsetV + (((double)System.currentTimeMillis() % HmfController.modoloMod) / HmfController.quotientMod));
            }
            else
            {
                tessellator.addVertex(vertices[i].x, vertices[i].y, vertices[i].z);
            }
        }
    }

    public Vertex calculateFaceNormal()
    {
        Vec3 v1 = Vec3.createVectorHelper(vertices[1].x - vertices[0].x, vertices[1].y - vertices[0].y, vertices[1].z - vertices[0].z);
        Vec3 v2 = Vec3.createVectorHelper(vertices[2].x - vertices[0].x, vertices[2].y - vertices[0].y, vertices[2].z - vertices[0].z);
        Vec3 normalVector = null;

        normalVector = v1.crossProduct(v2).normalize();

        return new Vertex((float) normalVector.xCoord, (float) normalVector.yCoord, (float) normalVector.zCoord);
    }
}
