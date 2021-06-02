package lights;

import org.junit.jupiter.api.Test;
import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Test rendering a basic image
 *
 * @author Dan
 */

public class LightsTests {
	private Scene scene1 = new Scene.sceneBuilder("Test scene").build();
	private Scene scene2 = new Scene.sceneBuilder("Test scene") //
			.ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15)).build();
	private Camera camera1 = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			.setViewPlaneSize(150, 150) //
			.setDistance(1000);
	private Camera camera2 = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			.setViewPlaneSize(200, 200) //
			.setDistance(1000);

	private static Geometry triangle1 = new Triangle( //
			new Point3D(-150, -150, -150), new Point3D(150, -150, -150), new Point3D(75, 75, -150));
	private static Geometry triangle2 = new Triangle( //
			new Point3D(-150, -150, -150), new Point3D(-70, 70, -50), new Point3D(75, 75, -150));
	private static Geometry sphere = new Sphere(50, new Point3D(0, 0, -50)) //
			.setEmission(new Color(java.awt.Color.BLUE)) //
			.setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(100));

	/**
	 * Produce a picture of a sphere lighted by a directional light
	 */
	@Test
	public void sphereDirectional() {
		scene1.geometries.add(sphere);
		scene1.lights.add(new DirectionalLight(new Color(500, 300, 0), new Vector(1, 1, -1)));

		ImageWriter imageWriter = new ImageWriter("lightSphereDirectional", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera1) //
				.setRayTracer(new RayTracerBasic(scene1));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a point light
	 */
	@Test
	public void spherePoint() {
		scene1.geometries.add(sphere);
		scene1.lights.add(new PointLight(new Color(500, 300, 0), new Point3D(-50, -50, 50))//
				.setKl(0.00001).setKq(0.000001));

		ImageWriter imageWriter = new ImageWriter("lightSpherePoint", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera1) //
				.setRayTracer(new RayTracerBasic(scene1));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void sphereSpot() {
		scene1.geometries.add(sphere);
		scene1.lights.add(new SpotLight(new Color(500, 300, 0), new Point3D(-50, -50, 50), new Vector(1, 1, -2)) //
				.setKl(0.00001).setKq(0.00000001));

		ImageWriter imageWriter = new ImageWriter("lightSphereSpot", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera1) //
				.setRayTracer(new RayTracerBasic(scene1));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a directional light
	 */
	@Test
	public void trianglesDirectional() {
		scene2.geometries.add(triangle1.setMaterial(new Material().setkD(0.8).setkS(0.2).setShininess(300)), //
				triangle2.setMaterial(new Material().setkD(0.8).setkS(0.2).setShininess(300)));
		scene2.lights.add(new DirectionalLight(new Color(300, 150, 150), new Vector(0, 0, -1)));

		ImageWriter imageWriter = new ImageWriter("lightTrianglesDirectional", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera2) //
				.setRayTracer(new RayTracerBasic(scene2));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a point light
	 */
	@Test
	public void trianglesPoint() {
		scene2.geometries.add(triangle1.setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(300)), //
				triangle2.setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(300)));
		scene2.lights.add(new PointLight.PointLightBuilder( new Point3D(10, -10, -130),new Color(500, 250, 250)).build() //
				.setKl(0.0005).setKq(0.0005));

		ImageWriter imageWriter = new ImageWriter("lightTrianglesPoint", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera2) //
				.setRayTracer(new RayTracerBasic(scene2));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light
	 */
	@Test
	public void trianglesSpot() {
		scene2.geometries.add(triangle1.setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(300)),
				triangle2.setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(300)));
		scene2.lights.add(new SpotLight(new Color(500, 250, 250), new Point3D(10, -10, -130), new Vector(-2, -2, -1)) //
				.setKl(0.0001).setKq(0.000005));

		ImageWriter imageWriter = new ImageWriter("lightTrianglesSpot", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera2) //
				.setRayTracer(new RayTracerBasic(scene2));
		render.renderImage();
		render.writeToImage();
	}
	@Test
	public void coolTest(){
		Scene scene3 = new Scene.sceneBuilder("cool and epic").background(new Color(57, 153, 189)).ambientLight(new AmbientLight(new Color(java.awt.Color.ORANGE), 0.15)).build();
		scene3.lights.add(new PointLight.PointLightBuilder(new Point3D(40,1,1), new Color(122,211,65)).build());
		scene3.lights.add(new DirectionalLight(new Color(11,22,33),new Vector(2,20,3)));
		scene3.lights.add(new SpotLight(new Color(44,34,65),new Point3D(5,7,-70),new Vector(10,5,20)));
		scene3.geometries.add(triangle1);
		scene3.geometries.add(sphere);
		ImageWriter imageWriter = new ImageWriter("coolTest", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera1) //
				.setRayTracer(new RayTracerBasic(scene3));
		render.renderImage();
		render.writeToImage();
	}
	@Test
	public void coolTest2(){
		Scene scene3 = new Scene.sceneBuilder("epic").background(new Color(57, 153, 189)).ambientLight(new AmbientLight(new Color(java.awt.Color.YELLOW), 0.15)).build();
		scene3.lights.add(new PointLight.PointLightBuilder(new Point3D(1,1,1), new Color(122,11,65)).build());
		scene3.lights.add(new DirectionalLight(new Color(11,90,140),new Vector(2,76,3)));
		scene3.lights.add(new SpotLight(new Color(44,34,5),new Point3D(5,7,9),new Vector(-9,5,7)));
		scene3.geometries.add(triangle1);
		scene3.geometries.add(triangle2);
		ImageWriter imageWriter = new ImageWriter("cooTest", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera1) //
				.setRayTracer(new RayTracerBasic(scene3));
		render.renderImage();
		render.writeToImage();
	}
	@Test
	public void coolTest3(){
		Scene scene3 = new Scene.sceneBuilder("cool epic").background(new Color(57, 153, 189)).ambientLight(new AmbientLight(new Color(java.awt.Color.YELLOW), 0.15)).build();
		scene3.lights.add(new PointLight.PointLightBuilder(new Point3D(-1,1,1), new Color(122,211,250)).build());
		scene3.lights.add(new DirectionalLight(new Color(200,20,33),new Vector(2,-76,3)));
		scene3.lights.add(new SpotLight(new Color(44,7,65),new Point3D(5,7,-9),new Vector(-9,5,7)));
		scene3.geometries.add(sphere);
		ImageWriter imageWriter = new ImageWriter("coTest", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera2) //
				.setRayTracer(new RayTracerBasic(scene3));
		render.renderImage();
		render.writeToImage();
	}

}
