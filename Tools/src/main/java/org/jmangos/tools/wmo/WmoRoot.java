/*******************************************************************************
 * Copyright (C) 2012 JMaNGOS <http://jmangos.org/>
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation; either version 2 of the License, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package org.jmangos.tools.wmo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javolution.util.FastTable;

import org.jmangos.tools.chunk.BaseChunk;
import org.jmangos.tools.wmo.chunks.WMOChunk;

public class WmoRoot {
	FastTable<BaseChunk> chunks = new FastTable<BaseChunk>();

	public static WmoRoot read(File f) throws IOException {
		FileInputStream fis = null;
		ByteBuffer bb = ByteBuffer.allocate((int) f.length());
		WmoRoot result = new WmoRoot();
		try {
			fis = new FileInputStream(f);
			fis.getChannel().read(bb);
			bb.rewind();
			bb.order(ByteOrder.LITTLE_ENDIAN);

			int glOffset = 0;
			while (glOffset < (int) f.length()) {
				BaseChunk ch = new WMOChunk().readChunkByHeader(bb, glOffset);
				glOffset = ch.getGlobalOffcet();
				result.chunks.add(ch);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (fis != null)
				fis.close();
		}
		return result;
	}

	public FastTable<BaseChunk> getChunks() {
		return chunks;
	}
}