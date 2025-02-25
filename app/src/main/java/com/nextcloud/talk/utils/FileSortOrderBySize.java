/*
 * Nextcloud Talk application
 *
 * @author Sven R. Kunze
 * @author Andy Scherzinger
 * Copyright (C) 2021 Andy Scherzinger <info@andy-scherzinger.de>
 * Copyright (C) 2017 Sven R. Kunze
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.nextcloud.talk.utils;

import com.nextcloud.talk.components.filebrowser.adapters.items.BrowserFileItem;

import java.util.Collections;
import java.util.List;

/**
 * Sorts files by sizes
 */
public class FileSortOrderBySize extends FileSortOrder {

    FileSortOrderBySize(String name, boolean ascending) {
        super(name, ascending);
    }

    /**
     * Sorts list by Size.
     *
     * @param files list of files to sort
     */
    public List<BrowserFileItem> sortCloudFiles(List<BrowserFileItem> files) {
        final int multiplier = isAscending ? 1 : -1;

        Collections.sort(files, (o1, o2) -> {
            if (!o1.getModel().isFile() && !o2.getModel().isFile()) {
                Long obj1 = o1.getModel().size;
                return multiplier * obj1.compareTo(o2.getModel().getSize());
            } else if (!o1.getModel().isFile()) {
                return -1;

            } else if (!o2.getModel().isFile()) {
                return 1;
            } else {
                Long obj1 = o1.getModel().getSize();
                return multiplier * obj1.compareTo(o2.getModel().getSize());
            }
        });

        return super.sortCloudFiles(files);
    }
}
