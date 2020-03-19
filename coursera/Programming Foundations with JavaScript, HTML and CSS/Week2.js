var img = new SimpleImage('smallpanda.png');

function setBlack(px){
    px.setRed(0);
    px.setGreen(0);
    px.setBlue(0);
    return px;
}

function addBorder(img, thickness){
    for (var px of img.values()){
        if (px.getX() <= thickness | px.getX() >= img.getWidth() - thickness){
            setBlack(px);
        }
        if (px.getY() <= thickness | px.getY() >= img.getHeight() - thickness){
            setBlack(px);
        }
    } 
    return img;
}
var img1 = addBorder(img, 10);
print(img1);
