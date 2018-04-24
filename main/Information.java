
package main;

import java.io.Serializable;

/**
 *  Information about a certain node
 *  Current informations contained: Name, Type, Image
 *  @author Aymane RAMACH, Michel NGATIMO, Morad SANBA
 */

public class Information implements Serializable
{
    /**
	 * SerialVersion
	 */
	private static final long serialVersionUID = 1L;

	/**
     * The name used as an identifier for the node
     */
    private String name;

    /**
     * The type of the node
     */
    private Type type;

    /**
     * An image representing the node
     */
    private String image;

    /**
     * Constructor with all the available informations
     * @param name The name of the node
     * @param type The type of the node
     * @param image The image of the node
     */
    public Information(String name, Type type, String image) {
        this.name = name;
        this.type = type;
        this.image = image;
    }
    
    /**
     * Constructor with  the available informations
     * @param name The name of the node
     * @param type The type of the node
     */
    public Information(String name,Type type)
    {
    		this.name = name;
        this.type = type;
    }

    /**
     * Get the name of the node
     * @return String containing the name of the node
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the node
     * @param name The new name of the node
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the type of the node
     * @return The type of the node
     */
    public Type getType() {
        return type;
    }

    /**
     * Set the type of the node
     * @param type The new type of the node
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Get the path of the image
     * @return Path of the image
     */
    public String getImage() {
        return image;
    }

    /**
     * Set the path of the image
     */
    public void setImage(String image) {
        this.image = image;
    }
}