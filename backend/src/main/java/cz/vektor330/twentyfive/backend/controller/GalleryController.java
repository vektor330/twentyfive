package cz.vektor330.twentyfive.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.vektor330.twentyfive.backend.model.GalleryDto;
import cz.vektor330.twentyfive.backend.model.PictureDto;

@RestController
public class GalleryController {

  @GetMapping("/gallery")
  public GalleryDto getGallery() {
    final List<PictureDto> pictures = List.of(new PictureDto("picture1",
            "https://c64hwz2ywdythczm.public.blob.vercel-storage.com/sample/photo1-FfAR69Cq8bPwjIvVEzeBoEPW1W0VWf.jpg"),
        new PictureDto("picture2",
            "https://c64hwz2ywdythczm.public.blob.vercel-storage.com/sample/photo2-ICX0Gc4RRWgzAnJmMbIWPYkU43rRHy.jpg"),
        new PictureDto("picture3",
            "https://c64hwz2ywdythczm.public.blob.vercel-storage.com/sample/photo3-fBnoLoWTnypcYRLb07niJtJ5CeogIZ.jpg"),
        new PictureDto("picture4",
            "https://c64hwz2ywdythczm.public.blob.vercel-storage.com/sample/photo4-HlpqzTYZv7azHfcF1JvjgdTwXVkvqs.jpg"),
        new PictureDto("picture5",
            "https://c64hwz2ywdythczm.public.blob.vercel-storage.com/sample/photo5-dnHZi66EGY0iCVCJ89pTqPjBpTsO9H.jpg"),
        new PictureDto("picture6",
            "https://c64hwz2ywdythczm.public.blob.vercel-storage.com/sample/photo6-86WAW58CZS3eTNiXgvrRfBTBLMmyzb.jpg"),
        new PictureDto("picture7",
            "https://c64hwz2ywdythczm.public.blob.vercel-storage.com/sample/photo7-AxrG0IOWSSiFXItXorJDMGlKCxo4V1.jpg"),
        new PictureDto("picture8",
            "https://c64hwz2ywdythczm.public.blob.vercel-storage.com/sample/photo8-qZWsy0DDA600XIvN3ZwsnC8c4WonFC.jpg"),
        new PictureDto("picture9",
            "https://c64hwz2ywdythczm.public.blob.vercel-storage.com/sample/photo9-iKF1adNnAA5hl7mXytYnSlQovX3d51.jpg"),
        new PictureDto("picture10",
            "https://c64hwz2ywdythczm.public.blob.vercel-storage.com/sample/photo10-kRtxnTFSqkRpw0ljSqtza6EgLbWU6V.jpg"),
        new PictureDto("picture11",
            "https://c64hwz2ywdythczm.public.blob.vercel-storage.com/sample/photo11-Dl5TrEujCl4KLKEsx4jy8W6oEB3jes.jpg"),
        new PictureDto("picture12",
            "https://c64hwz2ywdythczm.public.blob.vercel-storage.com/sample/photo12-4uWtXGWmxxu3Tsj2xMUVRoxQLgCrzu.jpg"),
        new PictureDto("picture13",
            "https://c64hwz2ywdythczm.public.blob.vercel-storage.com/sample/photo13-IHyKFEpQS8xGpESA17Ss55WRzu8ged.jpg"),
        new PictureDto("picture14",
            "https://c64hwz2ywdythczm.public.blob.vercel-storage.com/sample/photo14-5v4RwFrr0O8HBLZjcJz4ncELo7J99h.jpg"),
        new PictureDto("picture15",
            "https://c64hwz2ywdythczm.public.blob.vercel-storage.com/sample/photo15-a6gjg35tRZGaWodLk4rHZWu9zG1LgA.jpg"),
        new PictureDto("picture16",
            "https://c64hwz2ywdythczm.public.blob.vercel-storage.com/sample/photo16-9bUcUYwfwrkOiIJt5Xl3ZxZvGGSb17.jpg"),
        new PictureDto("picture17",
            "https://c64hwz2ywdythczm.public.blob.vercel-storage.com/sample/photo17-m6PiRbXD0sqASqh8R2efwqXu6EGYUu.jpg"),
        new PictureDto("picture18",
            "https://c64hwz2ywdythczm.public.blob.vercel-storage.com/sample/photo18-pSUagg8BvGA5qEQnxgTUkikpUCl86Z.jpg"),
        new PictureDto("picture19",
            "https://c64hwz2ywdythczm.public.blob.vercel-storage.com/sample/photo19-pN73XRq4XjbljmMVWw5rdVJ1AQYcM6.jpg"),
        new PictureDto("picture20",
            "https://c64hwz2ywdythczm.public.blob.vercel-storage.com/sample/photo20-vJXUtKmYT4W2IhmK2Mh2OXBJsvxYvo.jpg"),
        new PictureDto("picture21",
            "https://c64hwz2ywdythczm.public.blob.vercel-storage.com/sample/photo21-NaLKAHdpzoFCRCT77oXRjAVJVjiAJj.jpg"),
        new PictureDto("picture22",
            "https://c64hwz2ywdythczm.public.blob.vercel-storage.com/sample/photo22-gfl9PeEchrCBoqKwajdpvcSiJ8lVWP.jpg"),
        new PictureDto("picture23",
            "https://c64hwz2ywdythczm.public.blob.vercel-storage.com/sample/photo23-Npqshz3cYkCwL6g1D5zJTniAhqTID4.jpg"),
        new PictureDto("picture24",
            "https://c64hwz2ywdythczm.public.blob.vercel-storage.com/sample/photo24-E2m8r85gUFtIhOcyUD6heApbbvxxbO.jpg"),
        new PictureDto("picture25",
            "https://c64hwz2ywdythczm.public.blob.vercel-storage.com/sample/photo25-c2K0i8vWaI7BleRaVpDoxIi196E21j.jpg"));
    return new GalleryDto(pictures);
  }

}
