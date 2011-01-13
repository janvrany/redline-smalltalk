/*
Redline Smalltalk is licensed under the MIT License

Redline Smalltalk Copyright (c) 2010 James C. Ladd

Permission is hereby granted, free of charge, to any person obtaining a copy of this software
and associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE 
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package st.redline.smalltalk;

public class Bootstrapper {

	private final Smalltalk smalltalk;

	public Bootstrapper(Smalltalk smalltalk) {
		this.smalltalk = smalltalk;
	}

	public void bootstrap() {
		RObject classClass = bootstrapClassClass();
		RObject protoObjectClassMetaclass = bootstrapProtoObjectClassMetaclass(classClass);
		RObject protoObjectClass = bootstrapProtoObjectClass(protoObjectClassMetaclass);
		smalltalk.basicAtPut("Class", classClass);
		smalltalk.basicAtPut("ProtoObject", protoObjectClass);
	}

	private RObject bootstrapClassClass() {
		// TODO.JCL initialize super and metaclass.
		RObject classClass = RObject.classInstance();
		classClass.oop[RObject.CLASS_OFFSET] = null;
		classClass.oop[RObject.SUPERCLASS_OFFSET] = null;
		return classClass;
	}

	private RObject bootstrapProtoObjectClassMetaclass(RObject superclass) {
		RObject protoObjectClassMetaclass = RObject.classInstance();
		protoObjectClassMetaclass.oop[RObject.CLASS_OFFSET] = null;
		protoObjectClassMetaclass.oop[RObject.SUPERCLASS_OFFSET] = superclass;
		return protoObjectClassMetaclass;
	}

	private RObject bootstrapProtoObjectClass(RObject metaclass) {
		RObject protoObjectClass = RObject.classInstance();
		protoObjectClass.oop[RObject.CLASS_OFFSET] = metaclass;
		protoObjectClass.oop[RObject.SUPERCLASS_OFFSET] = null;
		return protoObjectClass;
	}
}
