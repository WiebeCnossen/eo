/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016-2021 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.eolang.phi;

/**
 * An object wrapping another one.
 *
 * @since 0.1
 */
public class PhOnce implements Phi {

    /**
     * The object fetched.
     */
    private final Data<Phi> object;

    /**
     * Ctor.
     *
     * @param data The object
     */
    public PhOnce(final Data<Phi> data) {
        this.object = new Data.Once<>(data);
    }

    @Override
    public final String toString() {
        return this.object.toString();
    }

    @Override
    public final Phi copy() {
        return this.object.take().copy();
    }

    @Override
    public final Attr attr(final int pos) {
        return this.object.take().attr(pos);
    }

    @Override
    public final Attr attr(final String name) {
        return this.object.take().attr(name);
    }
}
