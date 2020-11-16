
let faceapi, video, detections, imageDetections;
let viewImageMode = false, replaceFaceMode = false;
let videoFaceImage;

let cameraView = function (sketch) {

    sketch.setup = () => {
        let cameraCanvas = sketch.createCanvas(625, 437);
        cameraCanvas.position(0, 225);
        video = sketch.createCapture(sketch.VIDEO);
        video.size(sketch.width, sketch.height);
        video.hide();
        faceapi = ml5.faceApi(video, modelReady);
    }

    let modelReady = () => {
        faceapi.detect(gotResults);
        new p5(imageView);
    }

    let gotResults = (err, result) => {
        if (err) {
            console.log(err);
        }
        detections = result;

        sketch.image(video, 0, 0, sketch.width, sketch.height);
        if (detections) {
            if (detections.length > 0)
                drawBox(detections);
        }
        faceapi.detect(gotResults);
    }

    let drawBox = (detections) => {
        sketch.noFill();
        sketch.stroke(0, 255, 0);
        sketch.strokeWeight(2);
        for (let i = 0; i < detections.length; i++) {
            const alignedRect = detections[i].alignedRect;
            const { _x, _y, _width, _height } = alignedRect._box;
            console.log("FAce");
            console.log(alignedRect._box);
            videoFaceImage = sketch.get(_x, _y, _width, _height);
            sketch.rect(_x, _y, _width, _height);
        }
    }

};


new p5(cameraView);

let blurImage, orgImage;

let imageView = (sketch) => {

    sketch.preload = () => {
        orgImage = sketch.loadImage('test-image/image3.jpg');

    }

    sketch.setup = () => {
        let imageCanvas = sketch.createCanvas(625, 437);
        imageCanvas.position(625, 225);
        sketch.image(orgImage, 0, 0);
        faceapi.detect(orgImage, gotImageResults);
    }

    let replaceFaceInit = () => {
        sketch.clear();
        sketch.image(orgImage, 0, 0);
    }

    let imageViewInit = () => {
        sketch.clear();
        sketch.image(orgImage, 0, 0);
        sketch.filter(sketch.BLUR, 5);
        blurImage = sketch.get(0, 0, sketch.width, sketch.height);
        sketch.image(orgImage, 0, 0);
    }

    let gotImageResults = (err, result) => {
        if (err) console.log(err);
        imageDetections = result;
        console.log(imageDetections);
        console.log("image result");
    }

    let init = () => {
        sketch.clear();
        sketch.image(orgImage, 0, 0);
    }


    sketch.draw = () => {

        if (detections && detections.length > 0) {


            for (let i = 0; i < detections.length; i++) {
                const alignedRect = detections[i].alignedRect;
                const { _x, _y, _width, _height } = alignedRect._box;

                if (viewImageMode) {
                    sketch.clear();
                    const getOrgImage = orgImage.get(_x, _y, _width, _height);
                    sketch.image(blurImage, 0, 0);
                    sketch.image(getOrgImage, _x, _y);
                }

                if (replaceFaceMode) {
                    console.log("replace");
                    sketch.clear();
                    sketch.image(orgImage, 0, 0);
                    console.log(imageDetections.length);
                    for (let i = 0; i < imageDetections.length; i++) {
                        const imageAlignedRect = imageDetections[i].alignedRect;
                        console.log("image Face: ");
                        console.log(imageAlignedRect._box);
                        const _rx = imageAlignedRect._box["_x"];
                        const _rwidth = imageAlignedRect._box["_width"];
                        const _ry = imageAlignedRect._box["_y"];
                        const _rheight = imageAlignedRect._box["_height"];
                        const centerX = (_x + _width / 2);
                        const centerY = (_y + _height / 2);
                       
                        console.log("_x: " + _x + ", _width: " + _width);
                        console.log("_y: " + _y + ", _heigth: " + _height);
                        console.log("_rx: " + _rx + ", _rwidth: " + _rwidth);
                        console.log("_ry: " + _ry + ", rheigth: " + _rheight);
                        if (centerX >= _rx && centerX <= (_rx + _rwidth)
                            && centerY >= _ry && centerY <= _ry + _rheight) {
                            console.log(true);
                            console.log(videoFaceImage);
                            sketch.image(videoFaceImage, _rx, _ry, _rwidth, _rheight);
                            break;
                        } else {
                            sketch.clear();
                            sketch.image(orgImage, 0, 0);
                        }
                    }
                }
                sketch.fill(sketch.color(255, 204, 0));
                if (!viewImageMode && !replaceFaceMode) sketch.image(orgImage, 0, 0);
                sketch.circle(_x + _width / 2, _y + _height / 2, 10);
            }

        } else {
            init();
        }



    }

    sketch.keyReleased = () => {
        if (sketch.keyCode === 70) {
            console.log("click F");
            viewImageMode = false;
            replaceFaceMode = true;
            replaceFaceInit();
        } else if (sketch.keyCode === 86) {
            console.log("View Image true");
            viewImageMode = true;
            replaceFaceMode = false;
            imageViewInit();
        } else if (sketch.keyCode === 69) {
            viewImageMode = false;
            replaceFaceMode = false;
            init();
        }
        return false;
    }
}

