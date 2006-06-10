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
package org.seasar.portlet.tomahawk.multipart;

import java.util.Map;

import org.apache.commons.fileupload.FileItem;

/**
 * This interface handles the multpart request for inputFileUpload components.
 * 
 * @author <a href="mailto:shinsuke@yahoo.co.jp">Shinsuke Sugaya</a>
 */
public interface MultipartRequest
{

    // Hook for the t:inputFileUpload tag.
    public abstract FileItem getFileItem(String fieldName);

    /**
     * Not used internaly by MyFaces, but provides a way to handle the uploaded files
     * out of MyFaces.
     */
    public abstract Map getFileItems();

}