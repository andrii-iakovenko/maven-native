/*
 * The MIT License
 *
 * Copyright (c) 2004, The Codehaus
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.codehaus.mojo.natives.bcc;

import org.codehaus.mojo.natives.NativeBuildException;
import org.codehaus.mojo.natives.c.CCompiler;
import org.codehaus.mojo.natives.compiler.CompilerConfiguration;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.mojo.natives.compiler.Compiler;

import java.io.File;
import org.codehaus.plexus.component.annotations.Component;

@Component(role = Compiler.class, hint = "bcc", instantiationStrategy = "per-lookup")
public class BCCCompiler
    extends CCompiler
{

    protected Commandline getCommandLine( File src, File dest, CompilerConfiguration config )
        throws NativeBuildException
    {
        if ( config.getExecutable() == null || config.getExecutable().trim().length() == 0 )
        {
            config.setExecutable( "bcc32" );
        }

        Commandline cl = super.getCommandLine( src, dest, config );

        return cl;
    }

    protected String getOutputFileOption()
    {
        return "-o";
    }

}
