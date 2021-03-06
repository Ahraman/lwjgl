package com.bubble.opengl;

import java.util.ArrayList;
import java.util.List;

public class VertexBufferBuilder {
    private final List<IVertex> vertices;
    private final List<Integer> indices;
    private final List<VertexAttribute> attributes;
    private int beginningIndex = 0;
    private int offset = 0;

    public VertexBufferBuilder() {
        vertices = new ArrayList<>();
        indices= new ArrayList<>();
        attributes = new ArrayList<>();
    }

    public void addVertex(IVertex vertex) {
        vertices.add(vertex);
    }

    public void setAttribute(int location, int size, int stride) {
        attributes.add(new VertexAttribute(location, size, stride, offset));
        offset += size;
    }

    public void begin() {
        beginningIndex = vertices.size();
    }

    public void end() {
        beginningIndex = -1;
    }

    public void addTriangle(int a, int b, int c) {
        indices.add(a + beginningIndex);
        indices.add(b + beginningIndex);
        indices.add(c + beginningIndex);
    }

    public VertexBuffer getVAO() {
        final VertexBuffer vb = new VertexBuffer();
        final float[] vert = new float[vertices.size() * vertices.get(0).getSize()];
        int i = 0;
        for (IVertex v : vertices) {
            v.append(vert, i);
            i += v.getSize();
        }
        final int[] ind = new int[indices.size()];
        i = 0;
        for (Integer in : indices) {
            ind[i] = in;
            i ++;
        }
        final VertexAttribute[] attr = attributes.toArray(new VertexAttribute[0]);
        vb.upload(vert, ind, attr);
        return vb;
    }
}