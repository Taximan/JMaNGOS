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
package org.jmangos.tools.wmo.chunks.group;

import java.nio.ByteBuffer;

import org.jmangos.tools.chunk.BaseChunk;
import org.jmangos.tools.wmo.chunks.WMOChunk;

/**
 * Chunk <tt>MOPY</tt><br>
 * Materials for triangles.
 * 
 * @author MinimaJack
 * 
 */
public class MOPYChunk extends WMOChunk {
	public class MOPYEntry extends WMOChunk {
		public Unsigned8 Flags = new Unsigned8();
		/** Index in the root WMO file MOMT chunk. */
		public Unsigned8 materialId = new Unsigned8();
	}

	public MOPYEntry[] MOPYEntries;

	@Override
	public BaseChunk reads(ByteBuffer bb, int offset, int size) {
		MOPYEntries = new MOPYEntry[(int) (size / 2)];
		for (int i = 0; i < (size / 2); i++) {
			MOPYEntries[i] = new MOPYEntry();
			MOPYEntries[i].setByteBuffer(bb, offset + 2 * i);
		}
		setGlobalOffset(offset + size + HEADERSIZE);
		this.setByteBuffer(bb, offset);
		return this;
	}

	@SuppressWarnings("unused")
	private String getAllName() {
		String tmp = "";
		for (int i = 0; i < MOPYEntries.length; i++) {
			tmp += "\n\tFlags:" + MOPYEntries[i].Flags.get() + "\n\tMaterial:"
					+ MOPYEntries[i].materialId.get() + "\n";

		}
		return tmp;
	}

	public String toString() {
		return "[MOPYChunk]" + "\n\tMOPYEntries count: " + MOPYEntries.length;// +
																				// getAllName();
	}
}
