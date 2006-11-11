/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.portlet.wrapper;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletInputStream;

/**
 * This class is a dummy ServletInputStream.
 * 
 * @author <a href="mailto:shinsuke@yahoo.co.jp">Shinsuke Sugaya</a>
 * 
 */
public class ServletInputStreamWrapper extends ServletInputStream {
    InputStream inputStream;

    public ServletInputStreamWrapper(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.InputStream#available()
     */
    public int available() throws IOException {
        return inputStream.available();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.InputStream#close()
     */
    public void close() throws IOException {
        inputStream.close();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        return inputStream.equals(obj);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return inputStream.hashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.InputStream#mark(int)
     */
    public void mark(int readlimit) {
        inputStream.mark(readlimit);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.InputStream#markSupported()
     */
    public boolean markSupported() {
        return inputStream.markSupported();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.InputStream#read()
     */
    public int read() throws IOException {
        return inputStream.read();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.InputStream#read(byte[], int, int)
     */
    public int read(byte[] b, int off, int len) throws IOException {
        return inputStream.read(b, off, len);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.InputStream#read(byte[])
     */
    public int read(byte[] b) throws IOException {
        return inputStream.read(b);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.InputStream#reset()
     */
    public void reset() throws IOException {
        inputStream.reset();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.InputStream#skip(long)
     */
    public long skip(long n) throws IOException {
        return inputStream.skip(n);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return inputStream.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletInputStream#readLine(byte[], int, int)
     */
    public int readLine(byte[] b, int off, int len) throws IOException {
        if (len <= 0) {
            return 0;
        }
        int count = 0, c;

        while ((c = read()) != -1) {
            b[off++] = (byte) c;
            count++;
            if (c == '\n' || count == len) {
                break;
            }
        }
        return count > 0 ? count : -1;

    }

}
