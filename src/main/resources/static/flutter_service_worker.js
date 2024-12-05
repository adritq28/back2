'use strict';
const MANIFEST = 'flutter-app-manifest';
const TEMP = 'flutter-temp-cache';
const CACHE_NAME = 'flutter-app-cache';

const RESOURCES = {"assets/AssetManifest.bin": "95ec340aeff48ad51b1fa4963395e8ec",
"assets/AssetManifest.bin.json": "b901f695f058be5f0c11cfd6f6a347a9",
"assets/AssetManifest.json": "c138cd62937f97d50d49b09b812cf96a",
"assets/FontManifest.json": "dc3d03800ccca4601324923c0b1d6d57",
"assets/fonts/MaterialIcons-Regular.otf": "6cc99d1103fb0d42fdfffa93ea0ca634",
"assets/images/1.jpg": "6bd5294fb9b63cf1ce19b04ce8d91802",
"assets/images/2.jpg": "5862da0c009c9eb50273277d57be1f74",
"assets/images/223d7b70-2ca0-4d7d-bcdd-e650a9b0df2e.png": "2b5a4ffe3dc6209ced07ce67c2e8fd0c",
"assets/images/25.png": "074262e1ef0406d84dacab47c3393e7f",
"assets/images/26.png": "868ec9bceb78de0eb4c039c271de3f93",
"assets/images/2fe5261b-effc-420d-8749-5b2d36b8fbfb.png": "16f8086366a38b184af263c00070045c",
"assets/images/3.jpg": "e8b8f154c177e315329577ec31370665",
"assets/images/33db4830-efe7-4cc9-9218-183064aa58ba.png": "7abd47ec5ba9fe80bffcbeb8113164d1",
"assets/images/4.jpg": "d62513d2df30c606efc85bcc769b2d0d",
"assets/images/4443a872-9e0f-4ed7-b7f8-ae805c7312a6.png": "661d590e0771c3f32102aceb82af4c06",
"assets/images/46.jpg": "0edc60cdbd46089eebd238c9c9ca115c",
"assets/images/47.jpg": "3ccdd51a52370a39d64e02ab4cef8cc3",
"assets/images/5.jpg": "706b9ed861fbd5003f33c649c3c036d8",
"assets/images/538f2d12-50ec-4e09-bc04-9f66219ccd5f.png": "433307e1580eb58a5ad8d8081cb470b7",
"assets/images/6.jpg": "1a85d9015c1fa31bf1baf46dc50cbdd3",
"assets/images/6.png": "b6e098139b8d06fd5ce2cd7338bfcd79",
"assets/images/7101ed59-9669-4f3c-b116-45a6f1cce80b.png": "c89c76fd88278f15249816e416b998ca",
"assets/images/715607e2-73c6-4cbe-aa2d-ec1f910f82c4.png": "cb256de9982deb02ce2876fe1f03f553",
"assets/images/78c92de0-25e2-4e06-902c-e537c58731f4.png": "2681c32486191e2f920855f300c43313",
"assets/images/840d66a1-6417-433e-aa33-3c9106efb3e3.png": "07a058ee2bc8716813a3a08281787d3d",
"assets/images/8dd469e9-395b-47ea-bcc3-1e21c4fbfc76.png": "b02f1091eeefcd0e2639759fb5d015ec",
"assets/images/ACHACACHI.jpeg": "c4b7ff9fa350787239e12f23de7d9128",
"assets/images/ACHACACHI.png": "2f8d48a024262e4f3a8ac881cc05bc09",
"assets/images/amarillo.png": "26f4788c140d384d3e6fd7343e7526e7",
"assets/images/arveja.png": "1edb39823e62310e52d7ae0ebbe9b00c",
"assets/images/arveja_fase1.png": "63f6e553bd4fe78c952c340797ad5f29",
"assets/images/arveja_fase2.png": "4a355ed4cf033a5da80a613bbaf4c2f7",
"assets/images/arveja_fase3.png": "f5fcda3f3ca88f5362da77c949699dfb",
"assets/images/arveja_fase4.png": "0bce157989529a5ce85e403834ec8fe7",
"assets/images/arveja_fase5.png": "52b07670ccad40ecfcc40f6044cee00d",
"assets/images/b6364ec6-d2e7-4eee-ad0a-d9924c9db7e0.png": "cdd48b77f6501bee7d95694f767c931f",
"assets/images/be88adc5-ba6a-4575-ac7c-086bbadd9079.png": "efa78207c137a8647e4ea92d25e77a13",
"assets/images/BenedictoKara.jpeg": "57f249c69c9a4e776c82f4e017580c99",
"assets/images/BenedictoKara.png": "a3fe47dcec991076446eff84adbac5ae",
"assets/images/CALACOTO.JPG": "bfae07ee05d32fa6f9ad93e3f106098b",
"assets/images/CALACOTO.png": "b7ef091044c24f817aa41db25fd02651",
"assets/images/CARABUCO.jpg": "46e1202a3fe9cbb2646109d79b112ec7",
"assets/images/CARABUCO.png": "7054c22844da5bf5cffc77a2ef03968d",
"assets/images/d8d51567-5e1b-4572-8458-07c205f23161.png": "07a058ee2bc8716813a3a08281787d3d",
"assets/images/dd5e000d-940f-446e-a208-ecf020843045.png": "03a24b8d0491da7ab87e4b6d6f56c7d2",
"assets/images/doctor1.jpg": "2ae18702e39f9e802fe3dced31081af9",
"assets/images/doctor2.jpg": "7a8865274be2be79f3ccbc14ddf0407c",
"assets/images/doctor3.jpg": "6c6ed7f4011b7f926b3f1505475aba16",
"assets/images/doctor4.jpg": "c2baaff811e8e70ef23481c35bf7e2d6",
"assets/images/doctors.png": "152542f6fa2e8ffdf4025b60bf42a4f5",
"assets/images/durazno.png": "98cbe998ac9b8431370350e520bb93e8",
"assets/images/durazno_fase1.png": "09eaec50390348713e108348bdff8a78",
"assets/images/durazno_fase2.png": "31fa7b35c37a3da784f851dcf59e90e8",
"assets/images/durazno_fase3.png": "fa2a718498b8838e0d238dd5afecc206",
"assets/images/durazno_fase4.png": "50f548799472555e4ddb6a883afe4725",
"assets/images/durazno_fase5.png": "035fc1ff28fea1781a0872a967209355",
"assets/images/durazno_fase6.png": "47bcb63d1c63e8d85b2ba6dd7893b809",
"assets/images/durazno_fase7.png": "fdfcffa9c8f228fa5f4ebfc174402266",
"assets/images/EdgarQuispe.jpeg": "e311521cebc8ebd5811c4c269b06e91e",
"assets/images/ee4b9ee7-9d2b-4861-9f54-feb7214f98c8.png": "cf222a06767079a3f5e3954b26eac592",
"assets/images/ESCOMA.jpg": "ffdb0c2e96ceb2d91f34943622f64b41",
"assets/images/ESCOMA.png": "5ebed044fe6716093d10aa01657d39e2",
"assets/images/estacion.png": "a6d1632f2176f1886be3c80bf30c06a7",
"assets/images/fenologia.jpg": "dfd631b908acdfe4632822c6ac853790",
"assets/images/fondo.jpg": "8d9c218e16b0c5044b69a9d725c7df37",
"assets/images/FortunataAsquichu.png": "811855c6db04c1a4396ce35735c0c79e",
"assets/images/FranzAlejo.jpeg": "a6c5257ca8d892edb06a0f4dc3950df5",
"assets/images/haba.png": "468df01d4fb916b9ccc5e42a09f84b87",
"assets/images/haba_fase1.png": "6b355634fbf1070d7ac80fabe25c6db4",
"assets/images/haba_fase2.png": "0c17cac9ab93c4b57b3d70c02bca32db",
"assets/images/haba_fase3.png": "11d88cb7a672879aa1bccc05d730b38d",
"assets/images/haba_fase4.png": "b418e75e81c1306a862df585544a8d28",
"assets/images/haba_fase5.png": "9e7f83d34bcf6bd2fcb779e2b5cc547b",
"assets/images/haba_fase6.png": "e2cfd009ce3e019ed0bb2218046e32a5",
"assets/images/hombre.png": "f1f303351f25df6047cf91770832b0d9",
"assets/images/Imagen1.jpg": "e4647e5914b8d16b562f8233d45b915c",
"assets/images/JacintoCalle.jpeg": "b6f760affb83b0f5337ed4b7d73efd64",
"assets/images/JherilMamani.jpeg": "66e8e9f312356e977358252194148ce8",
"assets/images/JherilMamani.png": "d17416c0bb377bcee377874fedd2bdf2",
"assets/images/lined%2520heart.png": "43c9015f0b513c0c1a72552c97be47dc",
"assets/images/logo1.jpg": "0be836a60eec4c1915098d955ee904c5",
"assets/images/logo2.jpg": "729b5cd850150d3a5857cbd762796612",
"assets/images/logo3.png": "2f4fa1dcb073d424b7c3cabb6e34d7f6",
"assets/images/logo4.png": "40d063100e3f9f1bc25822f2c1f81862",
"assets/images/LURIBAY.JPG": "d9dbff7ff7a76cd70b8541042d42bee7",
"assets/images/LURIBAY.png": "902b571baf3046b0c73e8ecb088af386",
"assets/images/mujer.png": "0f1eb6ac61666ce9cd57500d783f826c",
"assets/images/PALCA.png": "3b7d70b68e63522a78657ba449f6ab9c",
"assets/images/papa.png": "a800f855feca8a57bd139cdea63c31da",
"assets/images/papa_fase1.png": "7ee807383542ccffe83fdf5a5e270875",
"assets/images/papa_fase2.png": "be87a33f25085e297a3e1362587e9eec",
"assets/images/papa_fase3.png": "cb5a76469289332fad3b98538d4a1906",
"assets/images/papa_fase4.png": "b0d5ea27d4f6aed35fa1d84adcf7f881",
"assets/images/papa_fase5.png": "d12ab2d771dfdb0ba35fb2e537327524",
"assets/images/papa_fase6.png": "f3b168129dd075f36f3d9ceed22a3b40",
"assets/images/rojo.png": "fec248cdebbb2ea47cdf9e8cffcc57bd",
"assets/images/SICA%2520SICA.JPG": "8f85bf123bbc654bb7746ec7b7fa8bf9",
"assets/images/SICA%2520SICA.png": "0e89d819a985b248a61cd419bde6cfbe",
"assets/images/SICASICA.png": "cd31fb96c17d35dc0e46e43fb44619cd",
"assets/images/SICASICA.webp": "2277f12932e7e70ba0a70345e911b7a1",
"assets/images/TARACO.jpg": "d710be9ed429323947c54955f2caf7db",
"assets/images/TARACO.png": "d6ef57116fc2aaa9a88c5a57939fa648",
"assets/images/verde.png": "1ca584306fe62675e5818f09dbd72d14",
"assets/NOTICES": "52a95773a181cc7a8bfc29eafcb9588d",
"assets/packages/cupertino_icons/assets/CupertinoIcons.ttf": "e986ebe42ef785b27164c36a9abc7818",
"assets/packages/flutter_map/lib/assets/flutter_map_logo.png": "208d63cc917af9713fc9572bd5c09362",
"assets/shaders/ink_sparkle.frag": "ecc85a2e95f5e9f53123dcaf8cb9b6ce",
"canvaskit/canvaskit.js": "c86fbd9e7b17accae76e5ad116583dc4",
"canvaskit/canvaskit.js.symbols": "38cba9233b92472a36ff011dc21c2c9f",
"canvaskit/canvaskit.wasm": "3d2a2d663e8c5111ac61a46367f751ac",
"canvaskit/chromium/canvaskit.js": "43787ac5098c648979c27c13c6f804c3",
"canvaskit/chromium/canvaskit.js.symbols": "4525682ef039faeb11f24f37436dca06",
"canvaskit/chromium/canvaskit.wasm": "f5934e694f12929ed56a671617acd254",
"canvaskit/skwasm.js": "445e9e400085faead4493be2224d95aa",
"canvaskit/skwasm.js.symbols": "741d50ffba71f89345996b0aa8426af8",
"canvaskit/skwasm.wasm": "e42815763c5d05bba43f9d0337fa7d84",
"canvaskit/skwasm.worker.js": "bfb704a6c714a75da9ef320991e88b03",
"favicon.png": "5dcef449791fa27946b3d35ad8803796",
"flutter.js": "c71a09214cb6f5f8996a531350400a9a",
"icons/Icon-192.png": "ac9a721a12bbc803b44f645561ecb1e1",
"icons/Icon-512.png": "96e752610906ba2a93c65f8abe1645f1",
"icons/Icon-maskable-192.png": "c457ef57daa1d16f64b27b786ec2ea3c",
"icons/Icon-maskable-512.png": "301a7604d45b3e739efc881eb04896ea",
"index.html": "54eea0026eea95438571c9c7b8741492",
"/": "54eea0026eea95438571c9c7b8741492",
"main.dart.js": "8e297092f234e7ae74f391d5bcb9a69a",
"manifest.json": "39402164860669c53ba17a128b5cc721",
"version.json": "9d3043dd0956a62eba746f6f39a854bf"};
// The application shell files that are downloaded before a service worker can
// start.
const CORE = ["main.dart.js",
"index.html",
"assets/AssetManifest.bin.json",
"assets/FontManifest.json"];

// During install, the TEMP cache is populated with the application shell files.
self.addEventListener("install", (event) => {
  self.skipWaiting();
  return event.waitUntil(
    caches.open(TEMP).then((cache) => {
      return cache.addAll(
        CORE.map((value) => new Request(value, {'cache': 'reload'})));
    })
  );
});
// During activate, the cache is populated with the temp files downloaded in
// install. If this service worker is upgrading from one with a saved
// MANIFEST, then use this to retain unchanged resource files.
self.addEventListener("activate", function(event) {
  return event.waitUntil(async function() {
    try {
      var contentCache = await caches.open(CACHE_NAME);
      var tempCache = await caches.open(TEMP);
      var manifestCache = await caches.open(MANIFEST);
      var manifest = await manifestCache.match('manifest');
      // When there is no prior manifest, clear the entire cache.
      if (!manifest) {
        await caches.delete(CACHE_NAME);
        contentCache = await caches.open(CACHE_NAME);
        for (var request of await tempCache.keys()) {
          var response = await tempCache.match(request);
          await contentCache.put(request, response);
        }
        await caches.delete(TEMP);
        // Save the manifest to make future upgrades efficient.
        await manifestCache.put('manifest', new Response(JSON.stringify(RESOURCES)));
        // Claim client to enable caching on first launch
        self.clients.claim();
        return;
      }
      var oldManifest = await manifest.json();
      var origin = self.location.origin;
      for (var request of await contentCache.keys()) {
        var key = request.url.substring(origin.length + 1);
        if (key == "") {
          key = "/";
        }
        // If a resource from the old manifest is not in the new cache, or if
        // the MD5 sum has changed, delete it. Otherwise the resource is left
        // in the cache and can be reused by the new service worker.
        if (!RESOURCES[key] || RESOURCES[key] != oldManifest[key]) {
          await contentCache.delete(request);
        }
      }
      // Populate the cache with the app shell TEMP files, potentially overwriting
      // cache files preserved above.
      for (var request of await tempCache.keys()) {
        var response = await tempCache.match(request);
        await contentCache.put(request, response);
      }
      await caches.delete(TEMP);
      // Save the manifest to make future upgrades efficient.
      await manifestCache.put('manifest', new Response(JSON.stringify(RESOURCES)));
      // Claim client to enable caching on first launch
      self.clients.claim();
      return;
    } catch (err) {
      // On an unhandled exception the state of the cache cannot be guaranteed.
      console.error('Failed to upgrade service worker: ' + err);
      await caches.delete(CACHE_NAME);
      await caches.delete(TEMP);
      await caches.delete(MANIFEST);
    }
  }());
});
// The fetch handler redirects requests for RESOURCE files to the service
// worker cache.
self.addEventListener("fetch", (event) => {
  if (event.request.method !== 'GET') {
    return;
  }
  var origin = self.location.origin;
  var key = event.request.url.substring(origin.length + 1);
  // Redirect URLs to the index.html
  if (key.indexOf('?v=') != -1) {
    key = key.split('?v=')[0];
  }
  if (event.request.url == origin || event.request.url.startsWith(origin + '/#') || key == '') {
    key = '/';
  }
  // If the URL is not the RESOURCE list then return to signal that the
  // browser should take over.
  if (!RESOURCES[key]) {
    return;
  }
  // If the URL is the index.html, perform an online-first request.
  if (key == '/') {
    return onlineFirst(event);
  }
  event.respondWith(caches.open(CACHE_NAME)
    .then((cache) =>  {
      return cache.match(event.request).then((response) => {
        // Either respond with the cached resource, or perform a fetch and
        // lazily populate the cache only if the resource was successfully fetched.
        return response || fetch(event.request).then((response) => {
          if (response && Boolean(response.ok)) {
            cache.put(event.request, response.clone());
          }
          return response;
        });
      })
    })
  );
});
self.addEventListener('message', (event) => {
  // SkipWaiting can be used to immediately activate a waiting service worker.
  // This will also require a page refresh triggered by the main worker.
  if (event.data === 'skipWaiting') {
    self.skipWaiting();
    return;
  }
  if (event.data === 'downloadOffline') {
    downloadOffline();
    return;
  }
});
// Download offline will check the RESOURCES for all files not in the cache
// and populate them.
async function downloadOffline() {
  var resources = [];
  var contentCache = await caches.open(CACHE_NAME);
  var currentContent = {};
  for (var request of await contentCache.keys()) {
    var key = request.url.substring(origin.length + 1);
    if (key == "") {
      key = "/";
    }
    currentContent[key] = true;
  }
  for (var resourceKey of Object.keys(RESOURCES)) {
    if (!currentContent[resourceKey]) {
      resources.push(resourceKey);
    }
  }
  return contentCache.addAll(resources);
}
// Attempt to download the resource online before falling back to
// the offline cache.
function onlineFirst(event) {
  return event.respondWith(
    fetch(event.request).then((response) => {
      return caches.open(CACHE_NAME).then((cache) => {
        cache.put(event.request, response.clone());
        return response;
      });
    }).catch((error) => {
      return caches.open(CACHE_NAME).then((cache) => {
        return cache.match(event.request).then((response) => {
          if (response != null) {
            return response;
          }
          throw error;
        });
      });
    })
  );
}
