package org.codehaus.mojo.natives.plugin;

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

import java.io.File;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.MojoExecutionException;
import org.codehaus.mojo.natives.NativeBuildException;
import org.codehaus.mojo.natives.exec.Exec;
import org.codehaus.mojo.natives.linker.Ranlib;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.Commandline;

/**
 * @goal ranlib
 * @phase package
 * @description ranlib a linker output file on unix
 *
 * @author <a href="dantran@gmail.com">Dan T. Tran</a>
 * @version $Id:$
 */
public class NativeRanlibMojo
    extends AbstractNativeMojo
{

    /**
     * @parameter expression="${project.artifactId}-${project.version}"
     * @optional
     */
    
    private String finalName;

    /**
     * @parameter expression="${project.artifact}"
     * @required
     * @readonly
     */
    private Artifact artifact;
       
    /**
     * @parameter expression="${component.org.codehaus.mojo.natives.linker.Ranlib}"
     * @required
     * @readonly
     */
  
    private Ranlib ranlib; 
    
    public void execute()
        throws MojoExecutionException
    {
        try 
        {
            File outputFile = new File ( this.outputDirectory.getAbsolutePath() + "/" + 
                                         this.finalName + "." + 
                                         this.artifact.getArtifactHandler().getExtension() );
            
            ranlib.run( outputFile );
        }
        catch ( NativeBuildException e )
        {
    		throw new MojoExecutionException( "Error executing ranlib.", e );
		}
    }
    
}
